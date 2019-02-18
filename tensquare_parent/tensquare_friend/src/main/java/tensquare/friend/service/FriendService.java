package tensquare.friend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tensquare.friend.Dao.FriendDao;
import tensquare.friend.Dao.NoFriendDao;
import tensquare.friend.pojo.Friend;
import tensquare.friend.pojo.NoFriend;

@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriend;

    public int addFriend(String userid, String friendid) {
        //1.查询是否重复添加
        Friend frienddao = this.friendDao.findByUseridAndFriendid(userid, friendid);
        if (frienddao != null){
            return 0;//0重复添加
        }
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        Friend frindUserid = friendDao.findByUseridAndFriendid(friendid, userid);
        if (frindUserid != null){
            friendDao.updateIslike("1",userid,friendid);
            friendDao.updateIslike("1",friendid,userid);
        }



        return 1;
    }

    public int addNoFriend(String userid, String friendid) {
        NoFriend frienddao = this.noFriend.findByUseridAndFriendid(userid, friendid);
        if (frienddao != null){
            return 0;//0重复添加
        }
        NoFriend friend = new NoFriend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        noFriend.save(friend);


        return 1;
    }

    public void deleteFriend(String userid, String friendid) {
        //1.把删除Friend表中数据
        friendDao.deletefriend(userid,friendid);

        //2.frind和user更新islike
        friendDao.updateIslike("0",friendid,userid);

        //21.在nofriend表中添加
        NoFriend noFriend1 = new NoFriend();
        noFriend1.setUserid(userid);
        noFriend1.setFriendid(friendid);
        noFriend.save(noFriend1);
    }
}
