package com.byteparity.npm.angular.portlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.byteparity.angular.portlet.constants.AngularMVCPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

/**
 * @author baps
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category="+AngularMVCPortletKeys.PORTLET_CATEGORY,
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name="+AngularMVCPortletKeys.PORTLET_DISPLAY_NAME,
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.name=" + AngularMVCPortletKeys.PORTLET_ID,
	},
	service = Portlet.class
)
public class AngularPortlet extends MVCPortlet {
}