package com.kcbgroup.loyalty.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.kcbgroup.loyalty.model.Channel;
import com.kcbgroup.loyalty.model.Currency;
import com.kcbgroup.loyalty.model.PointsMatrix;
import com.kcbgroup.loyalty.repository.ChannelRepository;
import com.kcbgroup.loyalty.repository.CurrencyRepository;
import com.kcbgroup.loyalty.repository.PointsMatrixRepository;

@Controller
public class PointsMatrixController {
	
	@Autowired
	private ChannelRepository channelRepository;
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Autowired
	private PointsMatrixRepository pointsMatrixRepository;
	
	@ResponseBody
	@GetMapping("/code-names/{channelCode}")
	public ResponseEntity<List<String>> codeNames(@PathVariable String channelCode) {
		List<Channel> channelList = channelRepository.findAll();
    	ListMultimap<String, String> channelMap = ArrayListMultimap.create();
    	
    	channelList.forEach(
            record -> channelMap.put(record.getCode() + " - " + record.getCategory(), record.getSubCategory())
	    );
    	return ResponseEntity.ok(channelMap.get(channelCode));
	}
    
    @ResponseBody
	@GetMapping("/points-currencies/{abbreviation}")
	public ResponseEntity<List<String>> getPointsCurrencies(@PathVariable String abbreviation) {
		List<Currency> currencyList = currencyRepository.findAll();
		ListMultimap<String, String> currencyMap = ArrayListMultimap.create();
		
		currencyList.forEach(
            record -> currencyMap.put(record.getAbbreviation(), record.getAbbreviation() + " - " + record.getName())
	    );
		return ResponseEntity.ok(currencyMap.get(abbreviation));
	}
    
    public ListMultimap<String, String> getCurrencyMap() {
    	List<Currency> currencyList = currencyRepository.findAll();
    	ListMultimap<String, String> currencyMap = ArrayListMultimap.create();
    	
    	currencyList.forEach(
            record -> currencyMap.put(record.getAbbreviation() + " - " + record.getName(), record.getAbbreviation())
	    );
    	return currencyMap;
	}
    
	public ListMultimap<String, String> getChannelMap() {
    	List<Channel> channelList = channelRepository.findAll();
    	ListMultimap<String, String> channelMap = ArrayListMultimap.create();
    	
    	channelList.forEach(
            record -> channelMap.put(record.getCode() + " - " + record.getCategory(), record.getSubCategory())
	    );
    	return channelMap;
	}
    
    @ResponseBody
	@GetMapping("/list-points-grid")
	public ResponseEntity<List<PointsMatrix>> listPointsGrid() {
		List<PointsMatrix> pointsMatrix = pointsMatrixRepository.findAll();
		return ResponseEntity.ok(pointsMatrix);
	}
	
	@PostMapping("/edit-points-form")
    public String editPointsForm(@ModelAttribute PointsMatrix pointsMatrix, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			List<String> channel = Splitter.on('-').trimResults().splitToList(pointsMatrix.getChannelCode());
			List<String> currency = Splitter.on('-').trimResults().splitToList(pointsMatrix.getCurrency());
			Long id = Long.valueOf(request.getParameter("id"));
			PointsMatrix pointsMatrixEdit = pointsMatrixRepository.getOne(id);
			
			pointsMatrixEdit.setChannelCode(channel.get(0));
			pointsMatrixEdit.setChannelName(channel.get(1));
			pointsMatrixEdit.setProductName(pointsMatrix.getProductName());
			pointsMatrixEdit.setProductCode(pointsMatrix.getProductCode());
			pointsMatrixEdit.setPointsValue(pointsMatrix.getPointsValue());
			pointsMatrixEdit.setPointsAmount(pointsMatrix.getPointsAmount());
			pointsMatrixEdit.setOnlineTransCode(pointsMatrix.getOnlineTransCode());
			pointsMatrixEdit.setIntlTransCode(pointsMatrix.getIntlTransCode());
			pointsMatrixEdit.setPosTxnCode(pointsMatrix.getPosTxnCode());
			pointsMatrixEdit.setCurrency(currency.get(0));
			pointsMatrixEdit.setDealCode(pointsMatrix.getDealCode());
			pointsMatrixEdit.setMinDay(pointsMatrix.getMinDay());
			pointsMatrixEdit.setMaxDay(pointsMatrix.getMaxDay());
			pointsMatrixEdit.setPos(pointsMatrix.getPos());
			pointsMatrixRepository.save(pointsMatrixEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "Points edited successfully");
    	}
    	catch(Exception e) {
    		redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    	}
    	
        return "redirect:/points-matrix-grid";
    }
	
	@ResponseBody
	@DeleteMapping("/delete-points/{id}")
	public ResponseEntity<String> deletePoints(@PathVariable Long id) {
		try {
			pointsMatrixRepository.deleteById(id);
			return ResponseEntity.ok("Points deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/points-matrix-form")
    public String getPointsMatrixForm(Model model) {
		ListMultimap<String, String> channelMap = getChannelMap();
		ListMultimap<String, String> currencyMap = getCurrencyMap();
		
	    model.addAttribute("channels", channelMap.keySet());
	    model.addAttribute("currencies", currencyMap.keySet());
        model.addAttribute("pointsMatrix", new PointsMatrix());
        return "parameters/points-matrix/pointsMatrixForm";
    }

    @PostMapping("/points-matrix-form")
    public String postPointsMatrixForm(@ModelAttribute PointsMatrix pointsMatrix, RedirectAttributes redirectAttributes) {
		try {
			List<String> channel = Splitter.on('-').trimResults().splitToList(pointsMatrix.getChannelCode());
			List<String> currency = Splitter.on('-').trimResults().splitToList(pointsMatrix.getCurrency());
			PointsMatrix pointsMatrixNew = new PointsMatrix();
			
			pointsMatrixNew.setChannelCode(channel.get(0));
			pointsMatrixNew.setChannelName(channel.get(1));
			pointsMatrixNew.setProductName(pointsMatrix.getProductName());
			pointsMatrixNew.setProductCode(pointsMatrix.getProductCode());
			pointsMatrixNew.setPointsValue(pointsMatrix.getPointsValue());
			pointsMatrixNew.setPointsAmount(pointsMatrix.getPointsAmount());
			pointsMatrixNew.setOnlineTransCode(pointsMatrix.getOnlineTransCode());
			pointsMatrixNew.setIntlTransCode(pointsMatrix.getIntlTransCode());
			pointsMatrixNew.setPosTxnCode(pointsMatrix.getPosTxnCode());
			pointsMatrixNew.setCurrency(currency.get(0));
			pointsMatrixNew.setDealCode(pointsMatrix.getDealCode());
			pointsMatrixNew.setMinDay(pointsMatrix.getMinDay());
			pointsMatrixNew.setMaxDay(pointsMatrix.getMaxDay());
			pointsMatrixNew.setPos(pointsMatrix.getPos());
			pointsMatrixRepository.save(pointsMatrixNew);
			redirectAttributes.addFlashAttribute("formSuccess", "Points captured successfully");
    	}
    	catch(Exception e) {
    		redirectAttributes.addFlashAttribute("formFailure", e.getMessage());
    	}
    	
        return "redirect:/points-matrix-form";
    }
    
    @GetMapping("/points-matrix-grid")
	public String pointsMatrixGrid(Model model) {
		ListMultimap<String, String> channelMap = getChannelMap();
		ListMultimap<String, String> currencyMap = getCurrencyMap();
		
		model.addAttribute("channels", channelMap.keySet());
	    model.addAttribute("currencies", currencyMap.keySet());
		return "parameters/points-matrix/pointsMatrixGrid";
	}

}
