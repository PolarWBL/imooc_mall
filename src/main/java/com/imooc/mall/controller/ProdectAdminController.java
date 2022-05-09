package com.imooc.mall.controller;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.common.Constant;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.request.AddProductReq;
import com.imooc.mall.model.request.UpdateProductReq;
import com.imooc.mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * 后台商品管理
 *
 * @author Boliang Weng
 */
@RestController
public class ProdectAdminController {
    @Resource
    private ProductService productService;

    @ApiOperation("后台添加商品")
    @PostMapping("/admin/product/add")
    public ApiRestResponse<Object> addProduct(@RequestBody @Valid AddProductReq addProductReq) {
        //添加商品
        productService.add(addProductReq);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台上传商品图片")
    @PostMapping("/admin/upload/file")
    public ApiRestResponse<Object> uploadProduct(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            //生成uuid文件名
            UUID uuid = UUID.randomUUID();
            String newFileName = uuid + suffix;

            System.out.println("上传的文件名称" + newFileName);

            //创建文件
            File fileDirectory = new File(Constant.FILE_UPLOAD_DIR);
            File destFile = new File(Constant.FILE_UPLOAD_DIR + newFileName);

            System.out.println("上传的文件地址" + destFile);

            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new ImoocMallException(ImoocMallExceptionEnum.MKDIR_FAILED);
                }
            }

            try {

                file.transferTo(destFile);
                URI uri = new URI(httpServletRequest.getRequestURL() + "");
                String path = getHost(uri) + "/images/" + newFileName;
                return ApiRestResponse.success(path);

            } catch (URISyntaxException | IOException e) {
                throw new ImoocMallException(ImoocMallExceptionEnum.UPLOAD_FAILED);
            }
        }else {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPLOAD_FAILED);
        }
    }

    private URI getHost(URI uri) {
        URI effectiveUri;
        try {
            effectiveUri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (URISyntaxException e) {
            effectiveUri = null;
        }

        return effectiveUri;
    }

    @ApiOperation("后台更新商品")
    @PostMapping("/admin/product/update")
    public ApiRestResponse<Object> updateProduct(@RequestBody @Valid UpdateProductReq updateProductReq) {
        //更新商品
        productService.update(updateProductReq);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台删除商品")
    @PostMapping("/admin/product/delete")
    public ApiRestResponse<Object> deleteProduct(@RequestParam Integer id) {
        //更新商品
        productService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台批量上下架商品")
    @PostMapping("/admin/product/batchUpdateSellStatus")
    public ApiRestResponse<Object> batchUpdateSellStatus(@RequestParam Integer[] ids, @RequestParam Integer sellStatus) {
        //更新(批量)上下架商品
        productService.batchUpdateSellStatus(ids, sellStatus);
        return ApiRestResponse.success();
    }


    @ApiOperation("后台商品列表")
    @GetMapping("/admin/product/list")
    public ApiRestResponse<Object> listProductForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        //商品列表（平铺）
        PageInfo<Product> pageInfo = productService.listProductForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }




}
