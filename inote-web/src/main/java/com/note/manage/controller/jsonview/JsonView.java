package com.note.manage.controller.jsonview;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JsonView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		if (model
				.containsKey("org.springframework.validation.BindingResult.string"))
			model.remove("org.springframework.validation.BindingResult.string");
		if (model
				.containsKey("org.springframework.validation.BindingResult.note"))
			model.remove("org.springframework.validation.BindingResult.note");
		// System.out.println(mapper.writeValueAsString(model));
		out.print(mapper.writeValueAsString(model));

	}

}
