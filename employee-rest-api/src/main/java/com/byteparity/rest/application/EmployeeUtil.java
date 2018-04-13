package com.byteparity.rest.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.byteparity.emp.model.Employee;
import com.byteparity.emp.service.EmployeeLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

public class EmployeeUtil {

	/**
	 * Add OR Update Employee
	 * 
	 * @param empId
	 * @param firstName
	 * @param lastName
	 * @param city
	 * @param dob
	 * @param contactNumber
	 * @param organization
	 * @throws PortalException
	 * @throws ParseException
	 */
	public static void addOrUpdateEmployee(long empId, String firstName, String lastName, String city, String dob,
			String contactNumber, String organization) throws PortalException, ParseException {
		Employee employee = null;
		if (empId != 0) {
			employee = EmployeeLocalServiceUtil.getEmployee(empId);
		} else {
			employee = EmployeeLocalServiceUtil.createEmployee(CounterLocalServiceUtil.increment());
		}
		Date dateOfBirth = StringToDate(dob);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setCity(city);
		employee.setDob(dateOfBirth);
		employee.setContactNumber(contactNumber);
		employee.setOrganization(organization);

		if (empId != 0) {
			employee.setModifiedDate(new Date());
			employee = EmployeeLocalServiceUtil.updateEmployee(employee);
		} else {
			employee.setCreateDate(new Date());
			employee = EmployeeLocalServiceUtil.addEmployee(employee);
		}
	}

	/**
	 * Return All Employees
	 * 
	 * @return
	 */
	public static JSONArray getEmployees() {
		JSONObject jsonObject = null;
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		List<Employee> employees = EmployeeLocalServiceUtil.getEmployees(-1, -1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		for (Employee employee : employees) {
			jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("empId", employee.getEmpId());
			jsonObject.put("firstName", employee.getFirstName());
			jsonObject.put("lastName", employee.getLastName());
			jsonObject.put("dob", dateFormat.format(employee.getDob()));
			jsonObject.put("city", employee.getCity());
			jsonObject.put("organization", employee.getOrganization());
			jsonObject.put("contactNumber", employee.getContactNumber());
			jsonObject.put("createDate", dateFormat.format(employee.getCreateDate()));
			jsonObject.put("modifiedDate", dateFormat.format(employee.getModifiedDate()));
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}

	/**
	 * Return Employee By empId
	 * 
	 * @param empId
	 * @return
	 * @throws PortalException
	 */
	public static JSONObject getEmployee(long empId) throws PortalException {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		Employee employee = EmployeeLocalServiceUtil.getEmployee(empId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		jsonObject.put("empId", employee.getEmpId());
		jsonObject.put("firstName", employee.getFirstName());
		jsonObject.put("lastName", employee.getLastName());
		jsonObject.put("dob", dateFormat.format(employee.getDob()));
		jsonObject.put("city", employee.getCity());
		jsonObject.put("organization", employee.getOrganization());
		jsonObject.put("contactNumber", employee.getContactNumber());
		jsonObject.put("createDate", dateFormat.format(employee.getCreateDate()));
		jsonObject.put("modifiedDate", dateFormat.format(employee.getModifiedDate()));
		return jsonObject;
	}

	/**
	 * Return Response
	 *
	 * @param responceCode
	 * @param responceMsg
	 * @param result
	 * @return
	 */
	public static String getResponseData(int responceCode, String responceMsg, Object result,
			boolean isHandleException) {
		JSONObject responceData = JSONFactoryUtil.createJSONObject();
		Object nullResponceData = new ArrayList<String>();
		responceData.put("responseCode", responceCode);
		responceData.put("responseDesc", responceMsg);
		if (!isHandleException) {
			if (Validator.isNotNull(result)) {
				responceData.put("data", result);
			} else {
				responceData.put("data", nullResponceData);
			}
		}
		return responceData.toJSONString();
	}

	/**
	 * Format String to Date
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	private static Date StringToDate(String date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}
}
