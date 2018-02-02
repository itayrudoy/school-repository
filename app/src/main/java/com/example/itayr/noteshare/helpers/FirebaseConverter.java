package com.example.itayr.noteshare.helpers;

import com.example.itayr.noteshare.data.Group;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by itayr on 2/2/2018.
 */

public class FirebaseConverter {


    /**
     * Converts a firebase snapshot of a group to a group object.
     * @param groupSnapshot the group snapshot you want to convert.
     * @return a group object with the right values, null if the snapshot was wrong.
     */
    public static Group convertToGroup(DocumentSnapshot groupSnapshot) {
        Map<String, Object> groupMap = groupSnapshot.getData();
        String groupId = groupSnapshot.getId();

        return new Group(groupId, groupMap.get("name").toString(), (Map<String, Boolean>) groupMap.get("usersIds"));
    }

    /**
     * Converts a Group object to a map so it can be inserted into firebase.
     * @param group the group object you want to convert.
     * @return a map representing the group object.
     */
    public static Map<String, Object> convertGroupToMap(Group group) {
        Map<String, Object> groupMap = new HashMap<>();

        groupMap.put("name", group.getName());
        groupMap.put("usersIds", group.getUsersIds());

        return groupMap;
    }

}
