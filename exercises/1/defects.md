```
java -jar ./GoatGoatCar.jar car goat 2147483648 4
<num_times> must be an integer greater than 0.
```
Max_int+1 is still a number greater than 0. Shouldn't say that it needs to be greater than 0. 

```
java -jar .\GoatGoatCar.jar car goat 10001 apple
Exception in thread "main" java.lang.NumberFormatException: For input string: "apple"
        at java.lang.NumberFormatException.forInputString(Unknown Source)
        at java.lang.Integer.parseInt(Unknown Source)
        at java.lang.Integer.parseInt(Unknown Source)
        at GoatGoatCar.main(GoatGoatCar.java:180)
```
Should catch that "apple" is NaN and send a message.

```
java -jar .\GoatGoatCar.jar car goat apple 1
<num_times> must be an integer greater than 0.
```
Should catch that "apple" is NaN and send a message

```
java -jar .\GoatGoatCar.jar car goat 100 4
Recommended minimum number of times is 100
```
Should allow this per requirements

```
C:\Users\nicka\Documents\CS1632_Fall2020\exercises\1>java -jar .\GoatGoatCar.jar car goat 100000000 1234213412
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at GoatGoatCar.calculate(GoatGoatCar.java:59)
        at GoatGoatCar.main(GoatGoatCar.java:186)
```
Should catch that number is too large 

```
java -jar .\GoatGoatCar.jar car goat 100 2147483648
Exception in thread "main" java.lang.NumberFormatException: For input string: "2147483648"
        at java.lang.NumberFormatException.forInputString(Unknown Source)
        at java.lang.Integer.parseInt(Unknown Source)
        at java.lang.Integer.parseInt(Unknown Source)
        at GoatGoatCar.main(GoatGoatCar.java:180)

java -jar .\GoatGoatCar.jar car goat 10001 3.5
Exception in thread "main" java.lang.NumberFormatException: For input string: "3.5"
        at java.lang.NumberFormatException.forInputString(Unknown Source)
        at java.lang.Integer.parseInt(Unknown Source)
        at java.lang.Integer.parseInt(Unknown Source)
        at GoatGoatCar.main(GoatGoatCar.java:180)
```
Max_int+1 and 3.5 is a number and not a string

```
java -jar .\GoatGoatCar.jar a a 1 1
Recommended minimum number of times is 100

Continue? [y/n] > Y
Thread 0: 1 iterations.
Calculating..


Switch:
a : 100.000%
a : 0.000%
-----------------------------
Stay:
a : 0.000%
a : 100.000%


java -jar .\GoatGoatCar.jar a a 1 1
Recommended minimum number of times is 100

Continue? [y/n] > N
Sorry, I don't know what N means!

Continue? [y/n] > n
Please retry with a higher number of times.
```
Accepts capital `Y` but not `N`

```
java -jar .\GoatGoatCar.jar car goat 10001.5 3
<num_times> must be an integer greater than 0.
```
10001.5 is greater than 0


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


ID: TEST-NO-ARGS
TEST CASE: `java -jar .\GoatGoatCar.jar`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and output `Usage: java -jar GoatGoatCar.jar <good_choice> <bad_choice> <num_times> <num_threads>`

ID: TEST-ONE-ARG
TEST CASE: `java -jar .\GoatGoatCar.jar a`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and output `Usage: java -jar GoatGoatCar.jar <good_choice> <bad_choice> <num_times> <num_threads>`

ID: TEST-TWO-ARG
TEST CASE: `java -jar .\GoatGoatCar.jar a b`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and output `Usage: java -jar GoatGoatCar.jar <good_choice> <bad_choice> <num_times> <num_threads>`

ID: TEST-THREE-ARG
TEST CASE: `java -jar .\GoatGoatCar.jar a b 1`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and output `Usage: java -jar GoatGoatCar.jar <good_choice> <bad_choice> <num_times> <num_threads>`

ID: TEST-FOUR-ARG
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10001 4`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Succeed and give results

-----------------------------------------------------

ID: TEST-ITERATION-UNDER-HUNDRED-LOW
TEST CASE: `java -jar .\GoatGoatCar.jar a b 1 1`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Give warning and ask if you want to continue.

ID: TEST-ITERATION-UNDER-HUNDRED-MED
TEST CASE: `java -jar .\GoatGoatCar.jar a b 50 2`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Give warning and ask if you want to continue.

ID: TEST-ITERATION-UNDER-HUNDRED-HIGH
TEST CASE: `java -jar .\GoatGoatCar.jar a b 99 2`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Give warning and ask if you want to continue.

ID: TEST-ITERATION-AT-HUNDRED
TEST CASE: `java -jar .\GoatGoatCar.jar a b 100 2`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Succeed and give results

			 IDENTIFIER: TEST-ITERATION-AT-HUNDRED
			 SUMMARY: Asked for confirmation you wanted to run
			 DESCRIPTION: Per requirements, "If the "number of times" argument is **less than 100**, the system shall issue a warning and ask the user if they wish to continue." Being at 100, system still asks.
			 REPRODUCTION STEPS: Use java version 1.8.0_231 while running the test case where "number of times" is equal to 100
			 EXPECTED BEHAVIOR: Succeed and give results without confirming the run
			 OBSERVED BEHAVIOR: Asked for confirmation on if you wanted to run and would either succeed and output results or stop depending on respsone. 

ID: TEST-ITERATION-OVER-HUNDRED
TEST CASE: `java -jar .\GoatGoatCar.jar a b 101 2`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Succeed and give results

-----------------------------------------------------

ID: TEST-ITERATION-NEGATIVE
TEST CASE: `java -jar .\GoatGoatCar.jar a b -1 1`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and output `<num_times> must be an integer greater than 0.`

ID: TEST-ITERATION-ZERO
TEST CASE: `java -jar .\GoatGoatCar.jar a b 0 1`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and output `<num_times> must be an integer greater than 0.`

ID: TEST-THREAD-NEGATIVE
TEST CASE: `java -jar .\GoatGoatCar.jar a b 1000 -1`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and output `<num_threads> must be an integer greater than 0.`

ID: TEST-THREAD-ZERO
TEST CASE: `java -jar .\GoatGoatCar.jar a b 1000 0`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and output `<num_threads> must be an integer greater than 0.`

-----------------------------------------------------

ID: TEST-ITERATION-MAX-INT
TEST CASE: `java -jar .\GoatGoatCar.jar a b 2147483647 2`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Succeed and output results

ID: TEST-ITERATION-MAX-INT+1
TEST CASE: `java -jar .\GoatGoatCar.jar a b 2147483648 2`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and explain why it can't run

			 IDENTIFIER: TEST-ITERATION-MAX-INT+1
			 SUMMARY: System fails and says "number of times" needs to be greater than zero.
			 DESCRIPTION: When putting MAX_INT+1 into "number of times", I assume the integer number overflows causing it to be a very lage negative number causing the system to output, "<num_times> must be an integer greater than 0."
			 REPRODUCTION STEPS: Use java version 1.8.0_231 while running the test case where "number of times" is greater than or equal to MAX_INT+1
			 EXPECTED BEHAVIOR: Fail and explain why it can't run
			 OBSERVED BEHAVIOR: Failed and output "<num_times> must be an integer greater than 0."

ID: TEST-THREAD-MAX-INT
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10001 2147483647`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Succeed and output results

			 IDENTIFIER: TEST-THREAD-MAX-INT
			 SUMMARY: System crashes with "OutOfMemoryError: Java heap space" error.
			 DESCRIPTION: After a certain point, there is an upper thread limit. Should you exceed that limit, your program will either outright crash from the getgo or attempt to run and crash part way into the execution. 
			 REPRODUCTION STEPS: Use java version 1.8.0_231 while running the test case where "number of threads" is greater than some upper limit of threads a system is able to allocate. 
			 EXPECTED BEHAVIOR: Succeed and output results
			 OBSERVED BEHAVIOR: Crashed with "OutOfMemoryError: Java heap space" error

ID: TEST-THREAD-MAX-INT+1
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10001 2147483648`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and explain why it can't run

			 IDENTIFIER: TEST-THREAD-MAX-INT+1
			 SUMMARY: System treated the given "number of threads" as a string and crashed with "NumberFormatException: For input string: "2147483648"" error.
			 DESCRIPTION: When you go over MAX_INT for "number of threads", the system treats it as a string. System has no check that "number of threads" needs to be a number and crashes
			 REPRODUCTION STEPS: Use java version 1.8.0_231 while running the test case where "number of threads" is greater than MAX_INT. 
			 EXPECTED BEHAVIOR: Fail and explain why it can't run
			 OBSERVED BEHAVIOR: System crash with "NumberFormatException: For input string: "2147483648"" error

ID: TEST-LARGE-NUMBERS
TEST CASE: `java -jar .\GoatGoatCar.jar car goat 100000000 1234213412`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Succeed and output results

			 IDENTIFIER: TEST-LARGE-NUMBERS
			 SUMMARY: System crashes with "OutOfMemoryError: Java heap space" error
			 DESCRIPTION: There exists some upper thread limit on the system. Exceeding that limit will cause your program to either outright crash or crash partway through execution
			 REPRODUCTION STEPS: Use java version 1.8.0_231 while running the test case where "number of threads" is greater some upper limit of threads a system is able to allocate. 
			 EXPECTED BEHAVIOR: Succeed and output results
			 OBSERVED BEHAVIOR: System crash with "OutOfMemoryError: Java heap space" error

ID: TEST-MED-NUMBERS
TEST CASE: `java -jar .\GoatGoatCar.jar car goat 100000 1000`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Succeed and output results

-----------------------------------------------------

ID: TEST-ITERATION-DECIMAL
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10001.5  10`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and explain why it can't run

			 IDENTIFIER: TEST-ITERATION-DECIMAL
			 SUMMARY: [A one sentence description of defect]
			 DESCRIPTION: [A detailed description of everything the tester discovered]
			 REPRODUCTION STEPS: [Preconditions + Steps to reproduce (similar to test case execution steps)]
			 EXPECTED BEHAVIOR: [What you expected according to requirements]
			 OBSERVED BEHAVIOR: [What you *ACTUALLY* saw]

ID: TEST-THREAD-DECIMAL
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10001 10.5`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and explain why it can't run

			 IDENTIFIER: [A unique number or string (e.g. BUG-ARGS-NUMBER-FIVE-ARGS)]
			 SUMMARY: [A one sentence description of defect]
			 DESCRIPTION: [A detailed description of everything the tester discovered]
			 REPRODUCTION STEPS: [Preconditions + Steps to reproduce (similar to test case execution steps)]
			 EXPECTED BEHAVIOR: [What you expected according to requirements]
			 OBSERVED BEHAVIOR: [What you *ACTUALLY* saw]

-----------------------------------------------------

ID: TEST-ITERATION-STRING
TEST CASE: `java -jar .\GoatGoatCar.jar a b apple 10`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and explain why it can't run

			 IDENTIFIER: [A unique number or string (e.g. BUG-ARGS-NUMBER-FIVE-ARGS)]
			 SUMMARY: [A one sentence description of defect]
			 DESCRIPTION: [A detailed description of everything the tester discovered]
			 REPRODUCTION STEPS: [Preconditions + Steps to reproduce (similar to test case execution steps)]
			 EXPECTED BEHAVIOR: [What you expected according to requirements]
			 OBSERVED BEHAVIOR: [What you *ACTUALLY* saw]

ID: TEST-THREAD-STRING
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10001 bananas`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and explain why it can't run

			 IDENTIFIER: [A unique number or string (e.g. BUG-ARGS-NUMBER-FIVE-ARGS)]
			 SUMMARY: [A one sentence description of defect]
			 DESCRIPTION: [A detailed description of everything the tester discovered]
			 REPRODUCTION STEPS: [Preconditions + Steps to reproduce (similar to test case execution steps)]
			 EXPECTED BEHAVIOR: [What you expected according to requirements]
			 OBSERVED BEHAVIOR: [What you *ACTUALLY* saw]

ID: TEST-ITERATION-NULL
TEST CASE: `java -jar .\GoatGoatCar.jar a b null 10`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and explain why it can't run

			 IDENTIFIER: [A unique number or string (e.g. BUG-ARGS-NUMBER-FIVE-ARGS)]
			 SUMMARY: [A one sentence description of defect]
			 DESCRIPTION: [A detailed description of everything the tester discovered]
			 REPRODUCTION STEPS: [Preconditions + Steps to reproduce (similar to test case execution steps)]
			 EXPECTED BEHAVIOR: [What you expected according to requirements]
			 OBSERVED BEHAVIOR: [What you *ACTUALLY* saw]

ID: TEST-THREAD-NULL
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10001 null`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
POSTCONDITIONS: Fail and explain why it can't run

			 IDENTIFIER: [A unique number or string (e.g. BUG-ARGS-NUMBER-FIVE-ARGS)]
			 SUMMARY: [A one sentence description of defect]
			 DESCRIPTION: [A detailed description of everything the tester discovered]
			 REPRODUCTION STEPS: [Preconditions + Steps to reproduce (similar to test case execution steps)]
			 EXPECTED BEHAVIOR: [What you expected according to requirements]
			 OBSERVED BEHAVIOR: [What you *ACTUALLY* saw]

-----------------------------------------------------

ID: TEST-UNDER-HUNDRED-LOWER-Y
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10 10`
		`y`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
				 2) Give `y` as response to "Continue [y/n]"
POSTCONDITIONS: Continue and give results

ID: TEST-UNDER-HUNDRED-UPPER-Y
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10 10`
		`Y`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
				 2) Give `Y` as response to "Continue [y/n]"
POSTCONDITIONS: Continue and give results

ID: TEST-UNDER-HUNDRED-LOWER-N
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10 10`
		`n`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
				 2) Give `n` as response to "Continue [y/n]"
POSTCONDITIONS: Stop program

ID: TEST-UNDER-HUNDRED-UPPER-N
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10 10`
		`N`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
				 2) Give `N` as response to "Continue [y/n]"
POSTCONDITIONS: Stop program

			 IDENTIFIER: [A unique number or string (e.g. BUG-ARGS-NUMBER-FIVE-ARGS)]
			 SUMMARY: [A one sentence description of defect]
			 DESCRIPTION: [A detailed description of everything the tester discovered]
			 REPRODUCTION STEPS: [Preconditions + Steps to reproduce (similar to test case execution steps)]
			 EXPECTED BEHAVIOR: [What you expected according to requirements]
			 OBSERVED BEHAVIOR: [What you *ACTUALLY* saw]

ID: TEST-UNDER-HUNDRED-RANDOM-CHAR
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10 10`
		`a`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
				 2) Give `a` as response to "Continue [y/n]"
POSTCONDITIONS: Ask again

ID: TEST-UNDER-HUNDRED-NULL
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10 10`
		`null`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
				 2) Give `null` as response to "Continue [y/n]"
POSTCONDITIONS: Ask again

ID: TEST-UNDER-HUNDRED-RANDOM-STRING
TEST CASE: `java -jar .\GoatGoatCar.jar a b 10 10`
		`somerandomstring`
PRECONDITIONS: When `java -version` is run, system output says "java version "1.8.0_231""
EXECUTION STEPS: 1) Run Test Case
				 2) Give `somerandomstring` as response to "Continue [y/n]"
POSTCONDITIONS: Ask again

