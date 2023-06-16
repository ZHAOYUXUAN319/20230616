package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Buken;
import com.example.demo.entity.Message;
import com.example.demo.entity.Shinsa;
import com.example.demo.entity.Tintai;
import com.example.demo.entity.User;
import com.example.demo.obj.BukenDto;
import com.example.demo.obj.MessageDto;
import com.example.demo.obj.ShinsaDto;
import com.example.demo.obj.TintaiDto;
import com.example.demo.obj.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BukenService;

import jakarta.servlet.http.HttpSession;

//import org.springframework.security.core.context.SecurityContextHolder;
@Controller
@SessionAttributes("isVip")
public class UserController {

	// 物件公開一覧
	@Autowired
	private BukenService bukenService;

	@GetMapping(value = "/user/Buken")
	public ModelAndView displayListBuken(Model model) {
		List<BukenDto> bukenList = bukenService.searchAll();
		System.out.println("物件情報取得しました。" + bukenList);
		model.addAttribute("bukenList", bukenList);

		ModelAndView modelAndView = new ModelAndView("/user/Buken");
		return modelAndView;
	}

	// 物件非公開一覧

	@GetMapping(value = "/user/BukenHikoukei")
	public ModelAndView displayListBukenhikoukei(Model model) {
		List<BukenDto> bukenList = bukenService.searchBukenHikoukei();
		System.out.println("物件情報取得しました。" + bukenList);
		model.addAttribute("bukenList", bukenList);

		ModelAndView modelAndView = new ModelAndView("/user/Buken");
		return modelAndView;
	}

	// ユーザ一覧
	@GetMapping(value = "/user/user")
	public ModelAndView displayListUser(Model model) {
		List<UserDto> userList = bukenService.searchAllUser();
		System.out.println("ユーザ情報取得しました。" + userList);
		model.addAttribute("userList", userList);

		ModelAndView modelAndView = new ModelAndView("/user/user");
		return modelAndView;
	}

	// 賃貸一覧
	@GetMapping(value = "/user/tintai")
	public ModelAndView displayListTintai(Model model) {
		List<TintaiDto> tintaiList = bukenService.searchAllTintai();
		System.out.println("賃貸情報取得しました。" + tintaiList);
		model.addAttribute("tintaiList", tintaiList);

		ModelAndView modelAndView = new ModelAndView("/user/tintai");
		return modelAndView;
	}

	// message一覧
	@GetMapping(value = "/user/message")
	public ModelAndView displayListMessage(Model model) {
		List<MessageDto> messageList = bukenService.searchAllMessage();
		System.out.println("ユーザ情報取得しました。" + messageList);
		model.addAttribute("messageList", messageList);

		ModelAndView modelAndView = new ModelAndView("/user/message");
		return modelAndView;
	}

	// 審査一覧
	@GetMapping(value = "/user/shinsa")
	public ModelAndView displayListShinsa(Model model) {
		List<ShinsaDto> shinsaList = bukenService.searchAllShinsa();
		System.out.println("審査情報取得しました。" + shinsaList);
		model.addAttribute("shinsaList", shinsaList);
		ModelAndView modelAndView = new ModelAndView("/user/shinsa");
		return modelAndView;
	}

	// 物件新規画面
	@GetMapping(value = "/user/BukenAdd")
	public String displayBukenAdd(Model model) {
		model.addAttribute("bukenDto", new BukenDto());
		return "/user/BukenAdd";
	}

	@PostMapping("/user/BukenAdd")
	public String addBuken(@ModelAttribute("bukenDto") BukenDto bukenDto,
			@RequestParam("image") MultipartFile imageFile) {
		Buken buken = new Buken();
		buken.setPropertyName(bukenDto.getPropertyName());
		buken.setAddress(bukenDto.getAddress());
		buken.setPropertyType(bukenDto.getPropertyType());
		buken.setPeriod(bukenDto.getPeriod());
		buken.setPropertyArea(bukenDto.getPropertyArea());
		buken.setPrice(bukenDto.getPrice());
		buken.setSyozokuCompanyId(bukenDto.getSyozokuCompanyId());
		buken.setStatus(bukenDto.getStatus());

		if (!imageFile.isEmpty()) {
			try {
				String fileName = imageFile.getOriginalFilename();
				String filePath = "C:/Users/hangt/eclipse-workspace/Fudosan2/src/main/resources/static/img/" + fileName;
				imageFile.transferTo(new File(filePath));
				buken.setImagePath(filePath);
			} catch (IOException e) {
				e.printStackTrace();

			}
		}

		bukenService.saveBuken(buken);

		return "redirect:/user/home";
	}

	// ユーザ新規画面
	@GetMapping(value = "/user/userAdd")
	public String displayUserAdd(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "/user/userAdd";
	}

	@PostMapping("/user/userAdd")
	public String addUser(@ModelAttribute("userDto") UserDto userDto) {
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());

		bukenService.saveUser(user);

		return "redirect:/login";
	}

	// Message新規
	@PostMapping("/user/messageAdd")
	public String addMessage(@ModelAttribute("messageDto") MessageDto messageDto) {
		Message message = new Message();
		message.setMessages(messageDto.getMessages());
		message.setStatus(messageDto.getStatus());

		bukenService.saveMessage(message);

		return "redirect:/user/home";
	}
	
	//お気に入り新規
	@PostMapping("/user/okiniiri")
	public String addOkiniiri(@RequestParam("id") Long bukenId) {
	    bukenService.insertOkiniiri(bukenId,session);
	    return "redirect:/user/home";
	}

	// message中のポイント言葉"物件,VIP,審査"の処理、
	@PostMapping("/user/messageUpdate")
	public String processMessageForm(MessageDto messageDto, RedirectAttributes redirectAttributes,
			HttpSession session) {

		bukenService.updateMessageStatus(messageDto);

		String messages = messageDto.getMessages();

		if (messages != null) {
			List<UserDto> userList = bukenService.searchAllUser();

			for (UserDto user : userList) {
				if (messages.contains(user.getUserName())) {
					bukenService.updateUserStatus(user);
					break;
				}
			}
			if (messages.contains("物件")) {
				Long propertyId = (Long) session.getAttribute("propertyId");
				redirectAttributes.addAttribute("propertyId", propertyId);
				return "redirect:/user/shinsaSetei";
			}
			if (messages.contains("審査")) {
				Long bukenId = extractBukenIdFromMessages(messages);
				System.out.println("審査した物件IDは：" + bukenId);
				ShinsaDto shinsaDto = new ShinsaDto();
				shinsaDto.setId(bukenId);
				bukenService.updateShinsaStatusByBukenId(bukenId);
				bukenService.insertTintai(bukenId);
			}
		}

		return "redirect:/user/home";
	}

	private Long extractBukenIdFromMessages(String messages) {

		int startIndex = 0;
		int endIndex = messages.indexOf("号商品");
		if (endIndex == -1) {
			return null;
		}

		String bukenIdStr = messages.substring(startIndex, endIndex);
		try {
			Long bukenId = Long.parseLong(bukenIdStr);
			return bukenId;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	// login
	@GetMapping("/user/home")
	public String showMyPage(Model model, HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		model.addAttribute("userName", userName);
		return "user/home";
	}

	// 審査新規
	@GetMapping("/user/shinsaSetei")
	public String shinsa(Model model, HttpSession session) {
		Long propertyId = bukenService.getMessagePropertyId(session);
		System.out.println("審査され物件ID：" + propertyId);
		model.addAttribute("propertyId", propertyId);
		model.addAttribute("shinsaDto", new ShinsaDto());
		return "user/shinsaSetei";
	}

	@PostMapping("/user/shinsaSetei")
	public String addShinsa(@ModelAttribute("shinsaDto") ShinsaDto shinsaDto,
			@RequestParam("propertyId") Long bukenId) {
		String dropdown1Value = shinsaDto.getDropdown1();
		String dropdown2Value = shinsaDto.getDropdown2();
		Shinsa shinsa = new Shinsa();
		shinsa.setBukenId(bukenId);
		shinsa.setShinsaA(dropdown1Value);
		shinsa.setShinsaB(dropdown2Value);
		shinsa.setStatus("進捗なし");
		bukenService.saveShinsa(shinsa);

		bukenService.insertMessageShinsa(session, dropdown1Value, dropdown2Value, bukenId);

		return "redirect:/user/shinsaSetei";
	}

	// 物件删除
	@PostMapping("/user/Buken/delete/{id}")
	public String deleteBuken(@PathVariable("id") Long propertyId) {
		// bukenService.deleteBukenById(propertyId);
		bukenService.deleteBuken(propertyId);
		return "redirect:/user/user";
	}

	// ユーザ删除
	@PostMapping("/user/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		bukenService.deleteUser(id);
		return "redirect:/user/home";
	}

	// 更新物件
	@GetMapping("/user/BukenEdit/{propertyId}")
	public String displayBukenEdit(@PathVariable Long propertyId, Model model) {
		BukenDto bukenDto = bukenService.getBukenById(propertyId);
		model.addAttribute("bukenDto", bukenDto);
		return "user/BukenEdit";
	}

	// 注文
	@PostMapping("/user/BukenEdit")
	public String updateBuken(@ModelAttribute("bukenDto") BukenDto bukenDto, UserDto userDto,
			@RequestParam("updateType") String updateType, RedirectAttributes redirectAttributes) {
		if (updateType.equals("status")) {
			bukenService.updateBukenStatus(bukenDto);
			Long propertyId = bukenDto.getPropertyId();
			bukenService.insertMessage(session, propertyId);

//			redirectAttributes.addFlashAttribute("message", userDto.getUserName() + " ユーザ注文しました。");
//			redirectAttributes.addFlashAttribute("userName", userDto.getUserName());

		} else {
			bukenService.updateBuken(bukenDto);
		}
		return "redirect:/user/home";
	}

	/**
	 * VIP申请
	 * 
	 * @return
	 */
	@GetMapping("/user/vip")
	public String showVIPApplicationForm() {
		return "user/home";
	}

	// VIP申请
	@PostMapping("/user/vip")
	public String applyForVIP() {

		bukenService.insertMessageVip(session);

		return "redirect:/user/home";
	}

	// ユーザ更新
	@GetMapping("/user/userEdit/{id}")
	public String displayUserEdit(@PathVariable Long id, Model model) {
		UserDto userDto = bukenService.getUserById(id);
		model.addAttribute("userDto", userDto);
		return "user/userEdit";
	}

	// ユーザ更新
	@PostMapping("/user/userEdit")
	public String updateUser(@ModelAttribute("userDto") UserDto userDto) {
		bukenService.updateUser(userDto);
		return "redirect:/user/user";
	}

	// 物件検索（IDで）

	public UserController(BukenService bukenService) {
		this.bukenService = bukenService;
	}

	@PostMapping("/submit")
	public ModelAndView submitForm(@RequestParam("uid") Long propertyId) {
		List<Buken> bukenList = bukenService.searchByUidValue(propertyId);

		ModelAndView modelAndView = new ModelAndView("user/Buken");
		modelAndView.addObject("bukenList", bukenList);
		return modelAndView;
	}

	// 賃貸検索（IDで）
	@PostMapping("/tintaiKensaku")
	public ModelAndView tintaiKensaku(@RequestParam("name") String name) {
	    List<Tintai> tintaiList = bukenService.tintai(name);

	    ModelAndView modelAndView = new ModelAndView("user/tintai");
	    modelAndView.addObject("tintaiList", tintaiList); 
	    return modelAndView;
	}


	// 期間検索
	@PostMapping("/submitkikan")
	public ModelAndView submitForm1(@RequestParam("fromdate") Date fromdate, @RequestParam("todate") Date todate) {
		List<Buken> bukenList = bukenService.searchByPeriod(fromdate, todate);

		ModelAndView modelAndView = new ModelAndView("user/Buken");
		modelAndView.addObject("bukenList", bukenList);
		return modelAndView;
	}

	// ログイン
	@Autowired
	private HttpSession session;

	@GetMapping("/login")
	public String showLoginForm() {
		return "user/login";
	}

	@Autowired
	private UserRepository userRepository;

//	@PostMapping("/login")
//	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session) {
//	    User user = userRepository.findByUserName(userName); // ユーザ名で情報を検査
//	    System.out.println(userName);
//
//	    if (user != null && user.getPassword().equals(password) && "VIP".equals(user.getStatus())) {
//	        boolean isVip = true;
//
//	        session.setAttribute("isVip", isVip);
//
//	        System.out.println("Login successful");
//	        return "redirect:/user/Buken";
//	    } else if (user != null && user.getPassword().equals(password) && !"VIP".equals(user.getStatus())) {
//	        boolean isVip = false;
//
//	        session.setAttribute("000", isVip);
//
//	        System.out.println("Login successful");
//	        return "redirect:/user/Buken";
//	    }else{
//	        System.out.println("Login failed");
//	        return "redirect:/login?error";
//	    }
//
//	}
	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password,
			HttpSession session, Model model) {
		User user = userRepository.findByUserName(userName);
		System.out.println(userName);

		if (user != null && user.getPassword().equals(password)) {
			boolean isVip = "VIP".equals(user.getStatus());

			session.setAttribute("isVip", isVip);
			session.setAttribute("userName", userName);
			System.out.println("Login successful");

			model.addAttribute("userName", userName);

			return "redirect:/user/home";
		} else {
			System.out.println("Login failed");
			return "redirect:/login?error";
		}
	}
//	@PostMapping("/login")
//	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session, HttpServletRequest request) {
//	    User user = userRepository.findByUserName(userName); 
//	    System.out.println(userName);
//
//	    if (user != null && user.getPassword().equals(password)) {
//	        boolean isVip = "VIP".equals(user.getStatus());
//
//	        // 无效化
//	        session.invalidate();
//
//	     
//	        HttpSession newSession = request.getSession(true);
//	        newSession.setAttribute("isVip", isVip);
//
//	        System.out.println("Login successful");
//	        return "redirect:/user/Buken";
//	    } else {
//	        System.out.println("Login failed");
//	        return "redirect:/login?error";
//	    }
//	}

}