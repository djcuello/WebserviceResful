/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Daniel
 */
@Path("Rest")
public class Rest {
    @GET
    @Path("/getdata")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDataInJSON(){
    return "test";
    }
    
    
}
