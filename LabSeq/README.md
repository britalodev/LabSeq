# LabSeq



Implement a REST service, using a JAVA1 <br />
framework, returning a value from the labseq <br />
sequence. <br />
Optionally implement a simple JavaScript2 web GUI to invoke the service. <br />
The labseq – l(n) - sequence is defined as follows: <br />
n=0 => l(0) = 0 <br />
n=1 => l(1) = 1 <br />
n=2 => l(2) = 0 <br />
n=3 => l(3) = 1 <br />
n>3 => l(n) = l(n-4) + l(n-3) <br />
Example of the first sequence values: <br />
0 <br />
1 <br />
0 <br />
1 <br />
1 <br />
1 <br />
1 <br />
2 <br />
2 <br />
2 <br />
3 <br />
[…] <br />
The endpoint created should be in the form <baseurl>/labseq/{n} where {n} <br />
represents the index of the sequence’s (single) value to return. The index may be any nonnegative integer number. <br />
The implemented service should use a caching mechanism to take advantage of previous
calculations to speed up future calculations. This caching mechanism must be used in the
algorithm’s intermediate calculations (if applicable), and not only in the endpoint’s <br />
invocations. <br />
The answer must include: <br />
• Source code <br />
• REST API documentation – Open API format (Swagger) <br />
• Execution instructions (containers or other) <br />
Java code development best practices will be considered in the evaluation of the proposed <br />
resolution. Calculation performance is also a plus - calculation of l(10000) must be correctly returned under 10s. <br />
If there are any doubts regarding specific issues of the exercise that may influence its <br />
implementation, the applicant must make assumptions and implement the exercise according <br />
to them (these assumptions should be included in the answer). <br />
