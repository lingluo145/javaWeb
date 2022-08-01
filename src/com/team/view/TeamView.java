package com.team.view;

import com.team.domain.Employee;
import com.team.domain.Programmer;
import com.team.service.NameListService;
import com.team.service.TeamException;
import com.team.service.TeamService;

import java.util.Scanner;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.team.view
 * @Auther:Chen
 * @CreateTime:2022--06--23 16--18
 * @Decription:T000
 */
public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();
    /**
     * @description:主界面显示
     * @author: Chen
     * @date: 2022/6/23 16:23
     * @param: []
     * @return: void
     **/
    public void enterMainMenu(){
        boolean loopFlag = true;
        char menu = 0;
        while(loopFlag){
            if(menu != '1'){
                listAllEmployee();
            }
            System.out.print("1-团队列表 2-添加团队成员 3-删除团队成员 4-退出系统 请选择1-4：");
            menu = TSUitility.readMenuSelection();
            switch(menu){
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.println("确认是否退出？(Y/N)");
                    char isExit = TSUitility.readConfirmSelection();
                    if(isExit == 'Y'){
                        loopFlag = false;
                    }
                    break;
            }
        }


    }
    /**
     * @description:显示所有员工的信息
     * @author: Chen
     * @date: 2022/6/23 16:23
     * @param: []
     * @return: void
     **/
    public void listAllEmployee(){
        System.out.println("----------------------------开发团队调度系统----------------------------");
        Employee[] employees = listSvc.getAllEmployees();
        if(employees == null || employees.length == 0){
            System.out.println("公司中没有任何员工信息！");
        }else{
            System.out.println("ID \t姓名\t\t年龄\t工资\t\t职位\t\t状态\t\t奖金\t\t股票\t\t\t领用设备");
            for(int i=0; i<employees.length; i++){
                System.out.println(employees[i]);
            }
            System.out.println("----------------------------------------------------------------------");
        }

    }
    /**
     * @description:查看开发团队的成员信息
     * @author: Chen
     * @date: 2022/6/23 16:28
     * @param: []
     * @return: void
     **/
    public void getTeam(){
        System.out.println("-------------------------查看开发团队的成员信息-------------------------");
        Programmer[] team = teamSvc.getTeam();
        if(team == null || team.length == 0){
            System.out.println("开发团队中没有成员！");
        }else{
            System.out.println("TID/ID\t姓名\t\t年龄\t\t工资\t\t\t职位\t\t\t奖金\t\t\t股票\n");
            for(int i=0; i<team.length; i++){
                System.out.println(team[i].getDetailsForTeam());
            }
        }
    }
    /**
     * @description:添加开发团队的成员
     * @author: Chen
     * @date: 2022/6/23 16:29
     * @param: []
     * @return: void
     **/
    public void addMember(){
        System.out.println("-------------------------添加开发团队的成员-------------------------");
        System.out.print("请输入需要添加的员工ID：");
        int id = TSUitility.readInt();

        try {
            Employee emp = listSvc.getEmploee(id);
            teamSvc.addMember(emp);
            System.out.println("添加成功！");
            //进入等待，直到用户按空格键继续...
            TSUitility.readReturn();
        } catch (TeamException e) {
            System.out.println("添加失败.原因：" + e.getMessage());
        }

    }
    /**
     * @description:删除开发团队的成员
     * @author: Chen
     * @date: 2022/6/23 16:30
     * @param: []
     * @return: void
     **/
    public void deleteMember(){
        System.out.println("-------------------------删除开发团队的成员-------------------------");
        System.out.print("请输入需要删除的员工TID：");
        int memberId = TSUitility.readInt();

        System.out.print("请确认是否删除？（Y/N）：");
        char isDelete = TSUitility.readConfirmSelection();
        if(isDelete == 'N'){
            return;
        }
        try{
            teamSvc.removeMember(memberId);
            System.out.println("删除成功！");
            //进入等待，直到用户按空格键继续...
            TSUitility.readReturn();
        }catch(TeamException e){
            System.out.println("删除失败，原因：" + e.getMessage());
        }



    }

    public static void main(String[] args){
        TeamView view = new TeamView();
        view.enterMainMenu();
    }
}
