#!/usr/bin/env bash

#######
## Convert xml test report to json and validate test result
#######
function validate_test() {
    if [[ -f $1 ]]; then
        if [[ -f out.json ]]; then
            rm -r out.json
        fi

        if ! command -v xml2json &>/dev/null; then
            echo "xml2json command not found"
            return 1
        fi

        if ! command -v jq &>/dev/null; then
            echo "jq command not found"
            return 1
        fi

        if ! xml2json $1 &>/dev/null; then
            echo "Failed to convert $1 to json"
            return 1
        fi

        if [[ -f out.json ]]; then
            # shellcheck disable=SC2002
            failed=$(cat out.json | jq -r ".testsuite.failures")
            # shellcheck disable=SC2002
            error=$(cat out.json | jq -r ".testsuite.errors")
            # shellcheck disable=SC2002
            test_name=$(cat out.json | jq -r ".testsuite.name")
            if [[ $failed == "0" && $error == "0" ]]; then
                echo "$test_name succeeded"
                return 0
            else
                echo "Test $test_name failed"
                return 1
            fi
        fi

    else
        return 1
    fi

}

for TEST_REPORT in *.xml; do
    if ! validate_test "$TEST_REPORT"; then
        exit 1
    fi
done

exit 0
