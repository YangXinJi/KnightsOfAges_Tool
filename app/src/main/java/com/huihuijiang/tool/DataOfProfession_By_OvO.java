package com.huihuijiang.tool;

import java.io.Serializable;

public class DataOfProfession_By_OvO implements Serializable {
    private double strength;//力量
    private double skill;//技巧
    private double speed;//敏捷
    private double physique;//体质
    private double magic;//感知
    private double will;//意志

    public double[] getAll(){
        return new double[]{
                this.strength,
                this.skill,
                this.speed,
                this.physique,
                this.magic,
                this.will
        };
    }

    public void setData(String profession){
        switch (profession) {
            case "新兵":
                this.strength = 0.123;
                this.skill = 0.123;
                this.speed = 0.123;
                this.physique = 0.123;
                this.magic = 0;
                this.will = 0;
                break;
            case "见习骑兵":
                this.strength = 0.23;
                this.skill = 0.15;
                this.speed = 0.105;
                this.physique = 0.165;
                this.magic = 0;
                this.will = 0;
                break;
            case "猎人":
                this.strength = 0.11;
                this.skill = 0.23;
                this.speed = 0.20;
                this.physique = 0.11;
                this.magic = 0;
                this.will = 0;
                break;
            case "轻步兵":
                this.strength = 0.205;
                this.skill = 0.11;
                this.speed = 0.23;
                this.physique = 0.105;
                this.magic = 0;
                this.will = 0;
                break;
            case "重步兵":
                this.strength = 0.19;
                this.skill = 0.17;
                this.speed = 0.06;
                this.physique = 0.23;
                this.magic = 0;
                this.will = 0;
                break;
            case "自学巫师":
                this.strength = 0;
                this.skill = 0.165;
                this.speed = 0.035;
                this.physique = 0.07;
                this.magic = 0.195;
                this.will = 0.185;
                break;
            case "轻骑兵":
                this.strength = 0.44;
                this.skill = 0.54;
                this.speed = 0.15;
                this.physique = 0.32;
                this.magic = 0;
                this.will = 0;
                break;
            case "重骑兵":
                this.strength = 0.54;
                this.skill = 0.25;
                this.speed = 0.15;
                this.physique = 0.51;
                this.magic = 0;
                this.will = 0;
                break;
            case "弓箭手":
                this.strength = 0.25;
                this.skill = 0.58;
                this.speed = 0.47;
                this.physique = 0.15;
                this.magic = 0;
                this.will = 0;
                break;
            case "弩手":
                this.strength = 0.33;
                this.skill = 0.54;
                this.speed = 0.15;
                this.physique = 0.43;
                this.magic = 0;
                this.will = 0;
                break;
            case "勇士":
                this.strength = 0.58;
                this.skill = 0.15;
                this.speed = 0.40;
                this.physique = 0.32;
                this.magic = 0;
                this.will = 0;
                break;
            case "斥候":
                this.strength = 0.25;
                this.skill = 0.40;
                this.speed = 0.58;
                this.physique = 0.22;
                this.magic = 0;
                this.will = 0;
                break;
            case "方阵步兵":
                this.strength = 0.32;
                this.skill = 0.47;
                this.speed = 0.15;
                this.physique = 0.51;
                this.magic = 0;
                this.will = 0;
                break;
            case "重甲枪兵":
                this.strength = 0.47;
                this.skill = 0.25;
                this.speed = 0.15;
                this.physique = 0.58;
                this.magic = 0;
                this.will = 0;
                break;
            case "德鲁伊":
                this.strength = 0;
                this.skill = 0;
                this.speed = 0.07;
                this.physique = 0.22;
                this.magic = 0.73;
                this.will = 0.44;
                break;
            case "牧师":
                this.strength = 0;
                this.skill = 0;
                this.speed = 0.07;
                this.physique = 0.15;
                this.magic = 0.51;
                this.will = 0.73;
                break;
            case "皇家骑兵":
                this.strength = 0.8;
                this.skill = 1.0;
                this.speed = 0.2;
                this.physique = 0.6;
                this.magic = 0;
                this.will = 0;
                break;
            case "掠袭骑兵":
                this.strength = 0.7;
                this.skill = 0.4;
                this.speed = 1.0;
                this.physique = 0.5;
                this.magic = 0;
                this.will = 0;
                break;
            case "重装骑兵":
                this.strength = 1.1;
                this.skill = 0.4;
                this.speed = 0.2;
                this.physique = 0.9;
                this.magic = 0;
                this.will = 0;
                break;
            case "统御骑兵":
                this.strength = 0.6;
                this.skill = 0.8;
                this.speed = 0.1;
                this.physique = 0.65;
                this.magic = 0;
                this.will = 0.45;
                break;
            case "恐惧骑兵":
                this.strength = 1.05;
                this.skill = 0.26;
                this.speed = 0.52;
                this.physique = 0.77;
                this.magic = 0;
                this.will = 0;
                break;
            case "长弓手":
                this.strength = 0.85;
                this.skill = 1.1;
                this.speed = 0.4;
                this.physique = 0.25;
                this.magic = 0;
                this.will = 0;
                break;
            case "游侠":
                this.strength = 0.45;
                this.skill = 1.05;
                this.speed = 0.85;
                this.physique = 0.25;
                this.magic = 0;
                this.will = 0;
                break;
            case "盾弩手":
                this.strength = 0.55;
                this.skill = 1.0;
                this.speed = 0.25;
                this.physique = 0.8;
                this.magic = 0;
                this.will = 0;
                break;
            case "重弩手":
                this.strength = 0.7;
                this.skill = 0.8;
                this.speed = 0.2;
                this.physique = 0.9;
                this.magic = 0;
                this.will = 0;
                break;
            case "狂战士":
                this.strength = 1.04;
                this.skill = 0.26;
                this.speed = 0.72;
                this.physique = 0.58;
                this.magic = 0;
                this.will = 0;
                break;
            case "大剑士":
                this.strength = 0.98;
                this.skill = 0.78;
                this.speed = 0.33;
                this.physique = 0.51;
                this.magic = 0;
                this.will = 0;
                break;
            case "刺客":
                this.strength = 0.25;
                this.skill = 1.0;
                this.speed = 1.1;
                this.physique = 0.25;
                this.magic = 0;
                this.will = 0;
                break;
            case "剑圣":
                this.strength = 0.79;
                this.skill = 0.58;
                this.speed = 1.04;
                this.physique = 0.19;
                this.magic = 0;
                this.will = 0;
                break;
            case "决斗士":
                this.strength = 0.6;
                this.skill = 1.05;
                this.speed = 0.65;
                this.physique = 0.3;
                this.magic = 0;
                this.will = 0;
                break;
            case "暗影刀客":
                this.strength = 0.58;
                this.skill = 0;
                this.speed = 0.655;
                this.physique = 0.235;
                this.magic = 1.13;
                this.will = 0;
                break;
            case "王室禁卫":
                this.strength = 0.6;
                this.skill = 0.85;
                this.speed = 0.15;
                this.physique = 1.0;
                this.magic = 0;
                this.will = 0;
                break;
            case "铁甲军士":
                this.strength = 0.85;
                this.skill = 0.45;
                this.speed = 0.2;
                this.physique = 1.1;
                this.magic = 0;
                this.will = 0;
                break;
            case "重甲僧侣":
                this.strength = 0.32;
                this.skill = 0.2;
                this.speed = 0.19;
                this.physique = 0.84;
                this.magic = 0.4;
                this.will = 0.65;
                break;
            case "圣堂铁卫":
                this.strength = 0.8;
                this.skill = 0.3;
                this.speed = 0.2;
                this.physique = 0.85;
                this.magic = 0;
                this.will = 0.45;
                break;
            case "大德鲁伊":
                this.strength = 0;
                this.skill = 0;
                this.speed = 0.32;
                this.physique = 0.2;
                this.magic = 1.3;
                this.will = 0.78;
                break;
            case "主教":
                this.strength = 0;
                this.skill = 0.;
                this.speed = 0.13;
                this.physique = 0.26;
                this.magic = 0.91;
                this.will = 1.3;
                break;
            case "炼金术士":
                this.strength = 0;
                this.skill = 0.58;
                this.speed = 0.19;
                this.physique = 0.4;
                this.magic = 0.52;
                this.will = 0.91;
                break;
            case "吟游诗人":
                this.strength = 0;
                this.skill = 0;
                this.speed = 0.59;
                this.physique = 0.255;
                this.magic = 1.3;
                this.will = 0.455;
                break;
            case "帝国密使":
                this.strength = 0;
                this.skill = 0;
                this.speed = 0;
                this.physique = 0.34;
                this.magic = 1.13;
                this.will = 1.13;
                break;
            default:
                this.strength = 0;
                this.skill = 0;
                this.speed = 0;
                this.physique = 0;
                this.magic = 0;
                this.will = 0;
                break;
        }
    }

    public double getStrength() {
        return strength;
    }

    public double getSkill() {
        return skill;
    }

    public double getSpeed() {
        return speed;
    }

    public double getPhysique() {
        return physique;
    }

    public double getMagic() {
        return magic;
    }

    public double getWill() {
        return will;
    }
}
