package com.bank.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticJSON {
  public void writeJSON(
          HttpServletRequest request,
          HttpServletResponse response,
          Object data) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
//    response.setHeader("Access-Control-Allow-Orgin", "*");
    PrintWriter out = response.getWriter();
    out.write(new ObjectMapper().writeValueAsString(data) );
    out.flush();
    out.close();
  }


}
