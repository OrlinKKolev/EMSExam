# Pair of employees who have worked together
## This application serves as an Employee Management System th Pair Identifier and is the final exam of Sirma Academy summer 2023

## Problem:
Create an application that identifies the pair of employees who
have worked together on common projects for the longest period
of time and the time for each of those projects.

#### Input data: CSV file with 4 columns. EmpID, ProjectID, DateFrom, DateTo

Sample input (partial data.:
```
EmpID, ProjectID, DateFrom, DateTo
143, 12, 2013-11-01, 2014-01-05
218, 10, 2012-05-16, NULL
143, 10, 2009-01-01, 2011-04-27
...
```
Sample output:
```
143, 218, 8
10, 5
12, 3
```

#### Mandatory requirements
1. DateTo can be NULL, equivalent to today.
2. We are interested in the number of days they worked together.
3. The input data must be loaded to the program from a CSV file.
4. More than one date format to be supported, extra points will be given if all
date formats are supported.
5. In a README.md file summarize your understanding for the task and your
algorithm.
6. Do not use external libraries CSV parsing.
7. Follow clean code conventions.

#### Bonus features
1. Implement persistence of the data
2. CRUD for Employees

#### Delivery
1. The task solution needs to be uploaded in GitHub

## Solution:
Solution is divided into several phases

#### PHASE ONE:
1. read the CSV by rows {done/console}
1. identify different date formats and reformat the dates in ISO format. {done/console}
1. save all resources in base memory. {done/console}

#### PHASE TWO:
2. read the resources and extract them in different data collections for further processing. {done/console}
2. process the collections to identify "the best team" {done/console}

#### PHASE THREE:
3. represent the result data {done/console}


## ALGORITHM:
-> Collections for all the projects. Returns List<projects>
```
List<Integer> projects = listAllProjects(resources);
[1,2,3...]
```
-> Map for each project by employeeid. Returns HashMap<projects, List<employeeid>>
```
HashMap<Integer, List<Integer>> projectsByParis = listProjectsByEmpId(resources, empIdQueue);
{1=[101,102,103], 2=[102,104], 4=[101,102,106]...}
 ``` 
-> Collection for all pairs. Returns List<Pairs>
```
Pair class has all necesary fields for pair definiton and method for duration calculation
```
-> Map for duration of pair working together. Reruns HashMap<pairKey, duration> durations = calculateDurations(pairs);
```
HashMap<String, Long> durations = calculateDurations(pairs);
{103|111=203, 101|106=1060, 101|105=1788...}


```


## Program data parameters
#### Employees ID's:
Data type is integer.
One employee can have only one record by certain project.


####  Projects ID's:
Data type is integer.

#### Dates:
-> Supported date formats:
```
[yyyyMMdd]
[yyyy-MM-dd]
[yyyy.MM.dd]
[MM/dd/yyyy]
[dd-MM-yyyy]
[dd.MM.yyyy]
```
-> When date is Null program gets today. Null handler is case insensitive.
```
Null
nuLL
nUlL
etc...
```
#### CSV test file:
Test data is generated on https://www.mockaroo.com/ with 11 employees and 9 projects, then is further processed is MSExcel to suit the requirements. Originally generated data is MOCK_DATA.csv. 
...
