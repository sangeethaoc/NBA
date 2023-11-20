NBA Project:

Here, we have 3 modules,

1. Warriors(https://www.nba.com/warriors)
2. Sixers(https://www.nba.com/sixers/)
3. Bulls(https://www.nba.com/bulls/)

We have implemented the automation of above 3 modules as Hybrid Framework(TestNG and Cucumber).

We have config. properties file which has the configuration details like URL for each module.

Project specific methods which are applicable for all the test cases are placed in BaseClass.

There are 3 main packages as part of Cucumber BDD framework,

1. features
2. steps
3. runner class - Which is the execution class glues fetaure file and step definition file.

We can execute the testcases from the following places,

1. Runner file(seperate runner for each module)
2. Testcases(placed in src/test)
3. TestNG.xml file

The reports generated will be available in ./reports folder of project directory with the following names,

1. ProductDetails.txt (report for CP)
2. ListOfFooterLinks.csv (report for DP2)
3. DuplicateFooterLinks.csv (report for DP2)

