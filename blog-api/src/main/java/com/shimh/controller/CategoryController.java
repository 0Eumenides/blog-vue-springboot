package com.shimh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.entity.Category;
import com.shimh.service.CategoryService;
import com.shimh.vo.CategoryVO;
/**
 * 文章分类api
 * 
 * @author shimh
 *
 * 2018年1月25日
 *
 */
@RestController
@RequestMapping(value="/categorys")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public Result listCategorys(){
		List<Category> categorys = categoryService.findAll();
		
		return Result.success(categorys);
	}
	
	@GetMapping("detail")
	public Result listCategorysDetail(){
		List<CategoryVO> categorys = categoryService.findAllDetail();
		
		return Result.success(categorys);
	}
	
	@GetMapping("/{id}")
	public Result getCategoryById(@PathVariable("id") Integer id){
		
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		Category category = categoryService.getCategoryById(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		r.setData(category);
		return r;
	}
	
	@PostMapping("/create")
	public Result saveCategory( @Validated @RequestBody Category category){
		
		Integer categoryId = categoryService.saveCategory(category);
		
		Result r = Result.success();
		r.simple().put("categoryId", categoryId);
		return r;
	}
	
	@PostMapping("/update")
	public Result updateCategory(@RequestBody Category category){
		Result r = new Result();
		
		if(null == category.getId()){
			r.setResultCode(ResultCode.USER_NOT_EXIST);
			return r;
		}
		
		Integer categoryId = categoryService.updateCategory(category);
		
		r.setResultCode(ResultCode.SUCCESS);
		r.simple().put("categoryId", categoryId);
		return r;
	}
	
	@GetMapping("/delete/{id}")
	public Result deleteCategoryById(@PathVariable("id")Integer id){
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		categoryService.deleteCategoryById(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		return r;
	}
	
	
	
}
