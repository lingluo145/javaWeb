package com.team.service;


import com.team.domain.Architect;
import com.team.domain.Designer;
import com.team.domain.Employee;
import com.team.domain.Programmer;
import com.team.service.TeamException;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.team.service
 * @Auther:Chen
 * @CreateTime:2022--06--23 14--37
 * @Decription:T000
 */
public class TeamService {
    private static int counter = 1;//给memberId赋值使用
    private final int MAX_MEMBER = 5;//限制开发团队的人数
    private Programmer[] team = new Programmer[MAX_MEMBER];//存放开发团队信息
    private int total;//记录开发团队中实际的人数

    public TeamService() {
        super();
    }

    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for(int i = 0; i<total; i++){
            team[i] = this.team[i];
        }
        return team;
    }
    /**
     * @description:将指定的员工加入到开发团队中
     * @author: Chen
     * @date: 2022/6/23 14:54
     * @param: [e]
     * @return: void
     **/
    public void addMember(Employee e) throws TeamException{
        //成员已满，无法添加
        if(total >= MAX_MEMBER){
            throw new TeamException("成员已满，无法添加");
        }
        //该成员不是开发人员，无法添加
        if(! (e instanceof  Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        //该成员已经在开发团队中
        if(isExist(e)){
            throw new TeamException("该成员已经在开发团队中");
        }
        //该成员已经是某团队的成员
        Programmer p = (Programmer)e;//一定不会出现ClassCastException异常
        if(("BUSY").equalsIgnoreCase(p.getStatus().getNAME())){
            throw new TeamException("该成员已经是某团队的成员");
        }
        //该员工正在休假，无法添加
        else if(("VOCATION").equalsIgnoreCase(p.getStatus().getNAME())){
            throw new TeamException("该员工正在休假，无法添加");
        }
        //团队中至多有一位架构师
        //团队中至多有俩位设计师
        //团队中至多有三位程序员

        //获取已有的数组中架构师、设计师、程序员的人数
        int numOfArch = 0; int numOfDes = 0; int numOfPro = 0;
        for(int i = 0; i<total; i++){
            if(team[i] instanceof Architect){
                numOfArch++;
            }else if(team[i] instanceof Designer){
                numOfDes++;
            }else{
                numOfPro++;
            }
        }
        if(p instanceof Architect){
            if(numOfArch>=1){
                throw new TeamException("团队中至多有一位架构师");
            }
        }else if (p instanceof Designer){
            if(numOfDes >= 2){
                throw new TeamException("团队中至多有俩位设计师");
            }
        }else{
            if(numOfPro >= 3){
                throw new TeamException("团队中至多有三位程序员");
            }
        }

        //将p（或e）添加到现有的team中
        team[total++] = p;//赋给的是地址，所以放前后都没关系
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);


    }
    /**
     * @description:判断指定的员工是否存在于开发团队中
     * @author: Chen
     * @date: 2022/6/23 15:05
     * @param: [e]
     * @return: boolean
     **/
    public boolean isExist(Employee e){
        for(int i = 0; i<total; i++){
            if(e.getId() == team[i].getId()){
                return true;
            }
        }
        return false;
    }
    /**
     * @description:从开发团队中删去成员
     * @author: Chen
     * @date: 2022/6/23 15:43
     * @param: [memberId]
     * @return: void
     **/
    public void removeMember(int memberId) throws TeamException {
        int i;
        for(i =0; i<total; i++){
            if(team[i].getMemberId() == memberId){
                team[i].setStatus(Status.FREE);

                break;
            }
        }
        //找到指定项后直接进行删除操作
        for(int j = i+1; j<total; j++){
            team[j-1] = team[j];
        }
        //将最后一项原有的位置置空
        team[--total] = null;

        if(i == total){
            throw new TeamException("找不到指定memberId的员工，删除失败！");
        }


    }
}
