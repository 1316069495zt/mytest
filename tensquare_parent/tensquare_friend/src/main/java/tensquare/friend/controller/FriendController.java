package tensquare.friend.controller;

import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tensquare.friend.client.UserClient;
import tensquare.friend.service.FriendService;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {



    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FriendService friendService;

    @Autowired
    private UserClient userClient;

    @DeleteMapping("/{friendid}")
    public Result deleteFriend(@PathVariable String friendid){
//1.验证
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null){
            return new Result(false, StatusCode.LOGINERROR,"权限不足");
        }
        String userid = claims.getId();


        friendService.deleteFriend(userid,friendid);
        userClient.addFanscountandFollowcount(userid,friendid,-1);

        return new Result(true, StatusCode.OK,"删除成功" );


    }

    @PutMapping("/like/{friendid}/{type}")
    public Result addFriend(@PathVariable String friendid,@PathVariable String type){
        //1.验证
         Claims claims = (Claims) request.getAttribute("claims_user");
         if (claims == null){
             return new Result(false, StatusCode.LOGINERROR,"权限不足");
         }
        String userid = claims.getId();

         if (type.equals("1")){

             int flag = friendService.addFriend(userid,friendid);
             if (flag == 1){

                 userClient.addFanscountandFollowcount(userid,friendid,1);
                 return new Result(true, StatusCode.OK,"添加成功");


             }else {
                 return new Result(false, StatusCode.ERROR,"请勿重复添加");

             }



         }else if (type.equals("2")){

           int flag =  friendService.addNoFriend(userid,friendid);

             if (flag == 1){

                 //userClient.addFanscountandFollowcount(userid,friendid,-1);
                 return new Result(true, StatusCode.OK,"添加成功");

             }else {
                 return new Result(false, StatusCode.ERROR,"请勿重复添加");

             }
         }else {

             return new Result(false, StatusCode.ERROR,"参数错误");

         }


    }
}
