package jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class test2 {

    public static void main(String[] args) {
        Claims itcast = Jwts.parser().setSigningKey("itcast")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1NTAyMTc2NTEsImV4cCI6MTU1MDIxNzcxMSwicm9sZSI6ImFkbWluIn0.MBEOcf7cvnDif7WiR6Wle8TUD_pKs_OEKRyBQBQwugQ")
                .getBody();
        System.out.println(itcast.getId());
        System.out.println(itcast.getIssuedAt());
        System.out.println(itcast.getSubject());
        System.out.println(itcast.get("role"));
        System.out.println(itcast.getExpiration());
    }
}
