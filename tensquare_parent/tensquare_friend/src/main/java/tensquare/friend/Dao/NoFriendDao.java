package tensquare.friend.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tensquare.friend.pojo.Friend;
import tensquare.friend.pojo.NoFriend;

public interface NoFriendDao extends JpaRepository<NoFriend,String> ,JpaSpecificationExecutor<NoFriend>{


    NoFriend findByUseridAndFriendid(String uid, String fid);


}
