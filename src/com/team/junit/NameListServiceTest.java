package com.team.junit;

import com.team.domain.Employee;
import com.team.service.NameListService;
import com.team.service.TeamException;
import com.team.view.TSUitility;
import org.junit.Test;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.team.junit
 * @Auther:Chen
 * @CreateTime:2022--06--22 22--50
 * @Decription:T000
 */
public class NameListServiceTest {

    @Test
    public void testGetAllEmployees(){
        NameListService service = new NameListService();
        Employee[] employees = service.getAllEmployees();
        for(int i = 0; i < employees.length; i++){
            System.out.println(employees[i]);
        }
    }

    @Test
    public void testGetEmployee(){
        NameListService service = new NameListService();
        int id = 6;
        try{
            Employee employee = service.getEmploee(id);
            System.out.println(employee);
        }catch(TeamException e){
            System.out.println(e.getMessage());
        }
    }
}
