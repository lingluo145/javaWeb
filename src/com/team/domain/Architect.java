package com.team.domain;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.team.domain
 * @Auther:Chen
 * @CreateTime:2022--06--22 17--08
 * @Decription:T000
 */
public class Architect extends Designer {
    private int stock;

    public Architect(){
        super();
    }

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment,bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String toString(){
        return super.getDedails() + "\t架构师\t" + getStatus() +"\t" + getBouns() +"\t" + stock + "\t" + getEquipment().getDscription();
    }

    public String getDetailsForTeam(){
        return getMemberId() + "/" + getId() + "\t\t" + getName() + "\t" + getAge() + "\t\t" + getSalary() + "\t\t架构师\t\t" + getBouns() + "\t\t" + getStock();
    }
}

