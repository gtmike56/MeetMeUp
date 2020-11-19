package com.example.meetmeup;

import java.util.LinkedList;

public class GroupsDummyContent {
    private static LinkedList<Group> groups;

    public static LinkedList<Group> getGroups(){
        if (groups == null){
            Group sales = new Group("Sales Buddies");
            sales.addMember(new User("Michael Scott","mscott@abc.com","123michael"));
            sales.addMember(new User("Jim Halpert","jhalpert@abc.com","123jim"));
            sales.addMember(new User("Dwight Schrute","dwight@abc.com","123dwight"));

            Group warehouse = new Group("Warehouse workers");
            sales.addMember(new User("Michael Scott","mscott@abc.com","123michael"));
            sales.addMember(new User("Roy Anderson","randerson@abc.com","123roy"));
            sales.addMember(new User("Darryl Phillbin","dphillbin@abc.com","123daryll"));

            groups = new LinkedList<>();
            groups.add(sales);
            groups.add(warehouse);
        }
        return groups;
    }

    public static void addGroup(Group newGroup){
        groups.add(newGroup);
    }
}
