package tensquare.friend.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tensquare.friend.pojo.Friend;

public interface FriendDao extends JpaRepository<Friend,String> ,JpaSpecificationExecutor<Friend>{


    Friend findByUseridAndFriendid(String uid,String fid);

    @Modifying
    @Query(value = "UPDATE tb_friend SET islike = ? WHERE userid = ? and friendid = ?",nativeQuery = true)
    void updateIslike(String isLike ,String uid ,String fid);

    @Modifying
    @Query(value ="delete  from tb_friend where userid =? and friendid = ?" ,nativeQuery = true)
    void deletefriend(String userid, String friendid);
}
