package com.example.controller.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Job;

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
	public String toJobList(Model model) {
		
	    try {
	    	
	    	String siteUrl = "https://employment.en-japan.com/search/search_list/?occupation_back=400000&caroute=0701&occupation=401000_401500_402000_402500_403000_403500_404000_404500_405000_405500_409000&keywordtext=Java";//■ 1.HTML全体を取得
	        Document documents = Jsoup.connect(siteUrl).get();

	        String siteName = "エン転職";
	        Elements companyName = documents.select(".nameSet .companyName .company");
//	        Elements jobType = documents.select("");
	        
	        String codingLanguages = "Java";
	        Elements location = documents.select(".dataArea .dataList");
	        String phoneNumber = "";
	        
	        Elements businessDetails = documents.select(".dataArea .dataList");
	        Elements url = documents.select(".buttonArea .toDesc");

//	        Elements acquisitionDate = documents.select("");	        
//	        Elements updateDate = documents.select("");
	        
	        Job job;
	    	List<Job> jobList = new ArrayList<>();
	    	
	    	String firstUrl = "https://employment.en-japan.com"; 
	    	String latterUrl = "&aroute=0&caroute=0701"; 
	    	
	    	
	        for (int i = 0; i < companyName.size(); i++) {
	        	job = new Job();

	        	job.setSiteName(siteName);
	        	job.setCompanyName(companyName.get(i).text());
	        	job.setJobType("システム開発");
	        	
	        	job.setCodingLanguages(codingLanguages);
	        	job.setLocation(location.get(i).children().last().children().last().text());
	        	job.setPhoneNumber(phoneNumber);
	        	
	        	job.setBusinessDetails(businessDetails.get(i).children().first().children().last().text());
	        	
	        	if (url.get(i).attr("href").contains("caroute=0701")) {
	        		job.setUrl(firstUrl + url.get(i).attr("href"));
				}else {
					job.setUrl(firstUrl + url.get(i).attr("href") + latterUrl);
				}
	        	
	        		        		        	
	        	jobList.add(job);
	        }	        
	        
	        model.addAttribute("jobList",jobList);
	        
	    }catch(IOException e) {
	        e.printStackTrace();
	    }
		
		return "job/job_list";
	}	

}
