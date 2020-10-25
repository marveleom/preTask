package com.mycompany.testproj;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private TestMapper testMapper;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );

		List<HashMap> resultMap = testMapper.selectTestData();
		model.addAttribute("resultMap", resultMap);

		return "home";
	}

	/**
	 * 1. 데이터파일(.csv)의 각 레코드를 데이터베이스에 저장
	 * @return
	 */
	@GetMapping(value = "/insData.do")
	public String insData(){
		return "insData";
	}



	String imagePath = "c:\\temp";

	@PostMapping(value = "/insDataProc.do")
	public ResponseEntity<HashMap> insDataProc(@RequestBody MultipartFile file1) {

		String folderPath = new SimpleDateFormat("yyyyMMdd").format(new Date());
		File Folder = new File(imagePath + "\\" + folderPath);
		if (!Folder.exists()) {
			try {
				Folder.mkdir();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		String fileName1 = file1.getOriginalFilename();
		String fileExc  = StringUtils.getFilenameExtension(fileName1);
		String fileName = String.format("%s.%s", makeRandomString(), fileExc);
		String fullPath = String.format("%s\\%s\\%s", imagePath, folderPath, fileName);

		Path path = Paths.get(fullPath).toAbsolutePath().normalize();
		try {
			Files.copy(file1.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			List<List<String>> lists = ReadCsv.readCSV(fullPath);
			if(lists.size() > 1) {
				Iterator<List<String>> iterator = lists.iterator();
				while(iterator.hasNext()) {
					List<String> next = iterator.next();
					String col = (String) next.get(0);
					if("호선".equals(col)) {

					} else {
						testMapper.insertPassengerInfo(next);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		HashMap hashMap = new HashMap();
		hashMap.put("fileUpload", "ok");


		return new ResponseEntity<HashMap>( hashMap, HttpStatus.OK);
	}


	private String makeRandomString() {
		try {
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			String randomNum = Integer.valueOf(prng.nextInt()).toString();
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] result = sha.digest(randomNum.getBytes());
			return hexEncode(result);
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
	private String hexEncode(byte[] input) {
		StringBuilder result = new StringBuilder();
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		for (int idx = 0; idx < input.length; ++idx) {
			byte b = input[idx];
			result.append(digits[(b & 0xf0) >> 4]);
			result.append(digits[b & 0x0f]);
		}
		return result.toString();
	}
	/**
	 * 2. 일평균 인원이 가장 많은 상위 10개의 역 명과 인원수 조회
	 * @return
	 */
	@GetMapping(value = "/selectTopTenByDayAvg.do")
	public String selectTopTenByDayAvg(){
		return "selectTopTenByDayAvg";
	}

	/**
	 * 3. 월 인원수 평균이 가장 낮은 역 명과 인원수 조회
	 * @return
	 */
	@GetMapping(value = "/selectLowestByMonAvg.do")
	public String selectLowestByMonAvg(){
		return "selectLowestByMonAvg";
	}

	/**
	 * 조건별로 조회할 수 있는 화면
	 * @return
	 */
	@GetMapping(value = "/selectMenu.do")
	public String selectDiffByMonNum(){
		return "selectMenu";
	}


	@GetMapping(value = "/login/auth.do")
	public String auth() {
		return "auth";
	}

	@PostMapping(value = "/login/authProcess.do")
	public ResponseEntity<HashMap> authProcess(HttpServletRequest request) {

		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		HashMap hashMap = new HashMap();
		if("nice".equals(userId) && "1234".equals(userPwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", userId);
			hashMap.put("login", "ok");
		}

		return new ResponseEntity<HashMap>( hashMap, HttpStatus.OK);
	}
}
