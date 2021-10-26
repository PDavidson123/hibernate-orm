package com.example.Service;

import com.example.Repository.TokenHashRepository;
import com.example.data.TokenHash;
import com.example.security.jwt.GenerateToken;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class TokenHashService {

    @Inject
    TokenHashRepository tokenHashRepository;

    public Response addValidTokenHash(SecurityContext ctx) {

        if(!checkTokenLogout(ctx)) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, (int) GenerateToken.expTime);

            if(tokenHashRepository.addValidTokenHash(new TokenHash(getSecurityContextHashCode(ctx), cal.getTime()))) {
                return Response.ok("Logout successful.").build();
            } else {
                return Response.status(400).entity("Can't logout.").build();
            }
        } else {
            return Response.status(400).entity("User logouted.").build();
        }
    }

    public boolean checkTokenLogout(SecurityContext ctx) {
        deleteInvalidTokens();

        return tokenHashRepository.isTokenLogout(getSecurityContextHashCode(ctx));
    }

    public void deleteInvalidTokens() {
        List<TokenHash> all =tokenHashRepository.getAllTokenHash();
        Date date = java.util.Calendar.getInstance().getTime();

        for(TokenHash tokenHash : all) {
            if(date.after(tokenHash.getExpTime())) {
                tokenHashRepository.deleteTokenHash(tokenHash);
            }
        }
    }

    private Long getSecurityContextHashCode(SecurityContext ctx) {
        return (long)(ctx.getUserPrincipal().getName() + ctx.isSecure() + ctx.getAuthenticationScheme()).hashCode();
    }

}
