package com.example.itayr.noteshare.helpers;

import com.example.itayr.noteshare.data.Group;
import com.example.itayr.noteshare.data.User;
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

        //check if it's the correct snapshot.
        if (!groupMap.containsKey("name") || !groupMap.containsKey("usersIds")) {
            return null;
        }

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

    /**
     * Converts a firebase snapshot of a user to a user object.
     * @param userSnapshot the user snapshot you want to convert.
     * @return a user object with right values, null if the snapshot was wrong.
     */
    public static User convertToUser(DocumentSnapshot userSnapshot) {
        Map<String, Object> userMap = userSnapshot.getData();
        String userId = userSnapshot.getId();

        //check if it's the correct snapshot.
        if (!userMap.containsKey("username")) {
            return null;
        }

        return new User(userId, (String) userMap.get("username"));
    }

    /**
     * Converts a user object to a map so it can be inserted into firebase.
     * @param user the user object you want to convert.
     * @return a map representing the user object.
     */
    public static Map<String, Object> convertUserToMap(User user) {
        Map<String, Object> userMap = new HashMap<>();

        userMap.put("username", user.getUsername());

        return userMap;
    }

}
