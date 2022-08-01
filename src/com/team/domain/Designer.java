package com.team.domain;


/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.team.domain
 * @Auther:Chen
 * @CreateTime:2022--06--22 17--07
 * @Decription:T000
 */
public class Designer extends Programmer{
    private  double bonus;

    public Designer(){
        super();
    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }

    public double getBouns() {
        return bonus;
    }

    public void setBouns(double bonus) {
        this.bonus = bonus;
    }

    public String toString(){
        return super.getDedails() + "\t设计师\t" + getStatus() + "\t" + bonus +"\t\t\t" + getEquipment().getDscription();
    }

    public String getDetailsForTeam(){
        return getMemberId() + "/" + getId() + "\t\t" + getName() + "\t" + getAge() + "\t\t" + getSalary() + "\t\t设计师\t\t" + getBouns();
    }
}
