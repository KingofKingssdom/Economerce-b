package com.caNhan.E_conomy.Service.Impl;


import com.caNhan.E_conomy.Dto.SpecificationDetailDTO;
import com.caNhan.E_conomy.Entity.ProductSpecification;
import com.caNhan.E_conomy.Entity.SpecificationDetail;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.ProductSpecificationRepository;
import com.caNhan.E_conomy.Repository.SpecificationDetailRepository;
import com.caNhan.E_conomy.Service.SpecificationDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecificationDetailImpl implements SpecificationDetailService {
    private SpecificationDetailRepository specificationDetailRepository;
    private ProductSpecificationRepository productSpecificationRepository;
    private ModelMapper modelMapper;
    @Autowired
    public SpecificationDetailImpl(
            SpecificationDetailRepository specificationDetailRepository,
            ProductSpecificationRepository productSpecificationRepository,
            ModelMapper modelMapper) {
        this.specificationDetailRepository = specificationDetailRepository;
        this.productSpecificationRepository = productSpecificationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SpecificationDetailDTO create(SpecificationDetailDTO specificationDetailDTO) {
        Optional<ProductSpecification> productSpecificationOptional = productSpecificationRepository
                .findById(specificationDetailDTO.getProductSpecificationId());
        SpecificationDetail specificationDetail = new SpecificationDetail();
        if(productSpecificationOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Không tạo được thông số chi tiet với thông số có id " + specificationDetailDTO.getProductSpecificationId());
        }
        else {
            specificationDetail.setLabelSpecification(specificationDetailDTO.getLabelSpecification());
            specificationDetail.setValueSpecification(specificationDetailDTO.getValueSpecification());
            specificationDetail.setProductSpecification(productSpecificationOptional.get());
        }
        SpecificationDetail saveSpecificationDetail = specificationDetailRepository.save(specificationDetail);
        return modelMapper.map(saveSpecificationDetail, SpecificationDetailDTO.class);
    }
}
