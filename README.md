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
Solution is divided into serveral phases

#### PHASE ONE:
1. read the CSV by rows
1. identify different date formats and reformat the dates in ISO format.
1. upload the input data in postgresDB with hibernate & JPA. Use @Entity and repository.saveall.
1. generate DDL for the DB in readme file

#### PHASE TWO:
2.1 Set up spring application with database connectivity
2....


## ALGORITHM:
-> Collection for each project. 
```
HashMap (<projectid>, List<employeeid>)
List<employeeid>
  -> If list length =2 
  -> else 
		examine duration of each employee and match. Save data in object Pair. Write all pairs in DB.
 ``` 
-> Collection for all pairs
```
SortedMap(<projectid>, List<Pair>)
```

-> Set by pairs <K,V>
...
