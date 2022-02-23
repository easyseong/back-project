package com.example.backproject.controller.v1;

import com.example.backproject.api.model.response.SingleResult;
import com.example.backproject.config.security.JwtProvider;
import com.example.backproject.config.security.JwtTokenProvider;
import com.example.backproject.service.ResponseService;
import com.example.backproject.service.SignService;
import com.example.backproject.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class SignController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @Resource(name="signService")
    private SignService signService;



    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@RequestParam String id, @RequestParam String password) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", id); param.put("password", password);
        User user = signService.getUser(param);
        String endPass = passwordEncoder.encode(user.getPassword());
        if (!passwordEncoder.matches(password, endPass)) throw new CEmailSigninFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
    }




}
