select * from employee;

select * from department;


--Join Query

select * 
from employee e, department d
where e.dno = d.dno;

--Join Query2

SELECT ENO, ENAME, JOB, MANAGER, HIREDATE, SALARY, COMMISSION,
       D.DNO, DNAME, LOC
FROM EMPLOYEE E, DEPARTMENT D
WHERE E.DNO = D.DNO