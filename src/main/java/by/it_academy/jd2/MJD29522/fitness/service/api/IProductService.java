package by.it_academy.jd2.MJD29522.fitness.service.api;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;

import java.util.UUID;

public interface IProductService {

    void addNewProduct(ProductCreateDTO productCreateDTO);

    void update(UUID uuid, long dtUpdate, ProductCreateDTO productCreateDTO);

    PageDTO<ProductDTO> getPage(int numberOfPage, int size);


}
