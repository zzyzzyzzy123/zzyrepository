package online.zzy.springbootmybatis.beans;



import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzy
 * @description 权限
 * @date 2019/6/30
 */
public class Authority {
    Set<String> set=null;
    private Integer id;
    private int userid;
    Set<String> roles;
    Set<String> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        try {
            JSONArray jsonArray=new JSONArray(roles);
            set=new HashSet<>();
            for (int i=0;i<jsonArray.length();i++){
                set.add(jsonArray.getString(i));
            }
            this.roles = set;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        try {
            JSONArray jsonArray=new JSONArray(permissions);
            set=new HashSet<>();
            for(int i=0;i<jsonArray.length();i++){
                set.add(jsonArray.getString(i));
            }
            this.permissions = set;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", userid=" + userid +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}
