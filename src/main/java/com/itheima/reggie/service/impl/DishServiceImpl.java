package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.DishFlavor;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Program: reggie_take_out
 * @Description: 餐品
 * @Author: ATao
 * @Create: 2022-11-09 16:24
 * @Since version-1.0
 **/

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    /**
     * 新增菜品,同事保存对应的口味数据
     *
     * @param dishDto
     */

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private DishMapper dishMapper;

    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表dish
        this.save(dishDto);
        Long dishId = dishDto.getId();

        //菜品口味每个都赋ID
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item)->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品口味数据到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);

    }

    /**
     * 回显
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);

        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> list = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(list);
        return dishDto;
    }

    /**
     * 修改菜品
     * @param dishDto
     */
    @Override
    public void uploadWithFlavor(DishDto dishDto) {
        this.updateById(dishDto);

        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);


    }

    @Override
    public void workbookForDish() throws IOException {
        //Create Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create file system using specific name
        FileOutputStream out = new FileOutputStream(
                new File("/Users/atao/document/Project/reggie_take_out/src/main/resources/xls/createworkbook.xlsx"));
        //write operation workbook using file out object
        workbook.write(out);
        out.close();
        System.out.println("createworkbook.xlsx written successfully");

        //Create blank workbook
        //Create a blank sheet

        XSSFSheet spreadsheet = workbook.createSheet(
                " Employee Info ");
        //Create row object
        XSSFRow row;
        //This data needs to be written (Object[])
        Map< String, Object[] > empinfo = new TreeMap< String, Object[] >();
        empinfo.put( "1", new Object[] {"EMP ID", "EMP NAME", "DESIGNATION" });
        empinfo.put( "2", new Object[] {"tp01", "Gopal", "Technical Manager" });
        empinfo.put( "3", new Object[] {"tp02", "Manisha", "Proof Reader" });
        empinfo.put( "4", new Object[] {"tp03", "Masthan", "Technical Writer" });
        empinfo.put( "5", new Object[] {"tp04", "Satish", "Technical Writer" });
        empinfo.put( "6", new Object[] {"tp05", "Krishna", "Technical Writer" });


        QueryWrapper<Dish> dishQueryWrapper = new QueryWrapper<>();
        dishQueryWrapper.eq("price",3800);
        List<Map<String, Object>> maps = dishMapper.selectMaps(dishQueryWrapper);
        System.out.println(maps);

        Map< String, Object[] > dish =new TreeMap< String, Object[] >();
        int i = 1;
        for (Map<String, Object> map: maps){
            i++;
            dish.put(i+"",map.entrySet().toArray());
        }


        //Iterate over data and write to sheet
        Set< String > keyid = dish.keySet();
        int rowid = 0;
        for (String key : keyid)
        {
            row = spreadsheet.createRow(rowid++);
            Object [] objectArr = dish.get(key);
            int cellid = 0;
            for (Object obj : objectArr)
            {
                System.out.println(obj);
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }


        //Write the workbook in file system
        out = new FileOutputStream(
                new File("/Users/atao/document/Project/reggie_take_out/src/main/resources/xls/createworkbook.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println(
                "Writesheet.xlsx written successfully" );


    }
}
