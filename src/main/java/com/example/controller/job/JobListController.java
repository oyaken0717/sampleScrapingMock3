package com.example.controller.job;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ユーザーのログイン時のコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/job-list")
public class JobListController {
		
	/**
	 * 求人一覧画面へ.
	 * 
	 * @return 求人一覧
	 */
	@RequestMapping("/to-job-list")
	public String toJobList() {
		return "job/job_list";
	}	

}
