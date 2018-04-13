package com.byteparity.rest.application;

import java.text.ParseException;
import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.osgi.service.component.annotations.Component;

import com.byteparity.emp.service.EmployeeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author baps
 */
@ApplicationPath("/emp")
@Component(immediate = true, service = Application.class)
public class EmployeeRestApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}
	
	@POST
	@Path("/addOrUpdateEmp")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addOrUpdateEmployee(String requestData) {
		org.json.JSONObject jsonObj = null;
		try {
			jsonObj = new org.json.JSONObject(requestData);
			long empId = jsonObj.getLong("empId");
			String firstName = (String) jsonObj.get("firstName");
			String lastName = (String) jsonObj.get("lastName");
			String city = (String) jsonObj.get("city");
			String dob = jsonObj.getString("dob");
			String contactNumber = jsonObj.getString("contactNumber");
			String organization = jsonObj.getString("organization");
			
			if (Validator.isNull(firstName)) {
				return EmployeeUtil.getResponseData(1, "Error while upload document.JSONObject[\"firstName\"] not found.",
						null, true);
			} else if (Validator.isNull(lastName)) {
				return EmployeeUtil.getResponseData(1, "Error while upload document.JSONObject[\"lastName\"] not found.",
						null, true);
			} else if (Validator.isNull(city)) {
				return EmployeeUtil.getResponseData(1,
						"Error while employee.JSONObject[\"city\"] not found.", null, true);
			} else if (Validator.isNull(dob)) {
				return EmployeeUtil.getResponseData(1, "Error while upload document.JSONObject[\"dob\"] not found.",
						null, true);
			} else if (Validator.isNull(contactNumber)) {
				return EmployeeUtil.getResponseData(1,
						"Error while employee.JSONObject[\"contactNumber\"] not found.", null, true);
			} else if (Validator.isNull(organization)) {
				return EmployeeUtil.getResponseData(1,
						"Error while employee.JSONObject[\"organization\"] not found.", null, true);
			}else {
				EmployeeUtil.addOrUpdateEmployee(empId, firstName, lastName, city, dob, contactNumber, organization);
				return EmployeeUtil.getResponseData(200, "success",null, false);
			}
		} catch (JSONException | PortalException | ParseException e) {
			return EmployeeUtil.getResponseData(1, "Error while add or update of employee." + e.getMessage(),
					null, true);
		}
	}

	@GET
	@Path("/employee/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEmployeesById(@PathParam("empId") long empId) {
		try {
			JSONObject jsonObject = EmployeeUtil.getEmployee(empId);
			return  EmployeeUtil.getResponseData(200, "Success",jsonObject, false);
		} catch (PortalException e) {
			return EmployeeUtil.getResponseData(100, "Error",e.getMessage(), true);
		}
	}

	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteEmployee(String requestData) {
		org.json.JSONObject jsonObj = null;
		try {
			jsonObj = new org.json.JSONObject(requestData);
			long empId = jsonObj.getLong("empId");
			EmployeeLocalServiceUtil.deleteEmployee(empId);
			return EmployeeUtil.getResponseData(200, "Success",null, false);
		} catch (JSONException | PortalException  e) {
			return EmployeeUtil.getResponseData(1, "Error while delete employee." + e.getMessage(),
					null, true);
		}
	}
	
	@GET
	@Path("/employees")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEmployees() {
		return EmployeeUtil.getResponseData(1, "Success",EmployeeUtil.getEmployees(), false);
	}
}