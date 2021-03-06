package com.bit.university.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.university.dao.StudyDao;
import com.bit.university.vo.StudyVo;

@Controller
public class DeleteStudyController {

	@Autowired
	private StudyDao s_dao;
	

	@GetMapping("/login/deleteStudy")
	@ResponseBody
	public int deleteStudyGet(HttpServletRequest request, int study_no) throws Throwable {
		
		// 이미지 파일이 있다면 삭제 후 스터디게시물 삭제
		String path = request.getRealPath("image");
		
		StudyVo s_vo = s_dao.getOneStudy(study_no);
		int re =s_dao.deleteStudy(study_no);
		
		if(re<=0) {
		} else {
			if(s_vo.getStudy_fname()!=null) {
				File file = new File(path + "/" +s_vo.getStudy_fname());
				file.delete();
			}
		}
		return re;
		
	}
	
}
