/*
 * Copyright 2002-2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.syrup.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syrup.model.Project;
import com.syrup.storage.IStorage;
import com.syrup.storage.StorageRegistry;

public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = -5485332140449853235L;

    private static IStorage store = StorageRegistry.SyrupStorage;

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	List<Project> projects = store.getProjects();
    	req.setAttribute("projects", projects);
    	
    	// Check for a default project.
    	String projectId = req.getParameter("projectId");
    	Project proj = null;
    	try{
    		proj = store.getProjectById(new Long(projectId));
    	}catch(Exception e){
    		//
    	}
    	if(proj==null && projects!=null && projects.size()>0){
    		proj = projects.get(0);
    	}
    	req.setAttribute("project", proj);
        RequestDispatcher dispatch = req.getRequestDispatcher("home.jsp");

        dispatch.forward(req, resp);
    }

}
