package com.gmart.gmart_api.service;

import com.gmart.gmart_api.dto.sellerDto.SellerRequestDto;
import com.gmart.gmart_api.dto.sellerDto.SellerResponseDto;
import com.gmart.gmart_api.exceptions.SellerAlreadyExistsException;
import com.gmart.gmart_api.exceptions.SellerNotFoundException;
import com.gmart.gmart_api.exceptions.UserNotFoundException;
import com.gmart.gmart_api.mappers.SellerMapper;
import com.gmart.gmart_api.model.Seller;
import com.gmart.gmart_api.model.User;
import com.gmart.gmart_api.repository.SellerRepository;
import com.gmart.gmart_api.repository.UserRepository;
import com.gmart.gmart_api.service.impl.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;

    @Override
    public SellerResponseDto createSeller(String userId, SellerRequestDto sellerRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with id " + userId + " not found")
        );

        if (sellerRepository.existsById(userId)) {
            throw new SellerAlreadyExistsException("Seller already exists for user with ID " + userId);
        }

        Seller seller = sellerMapper.toSeller(sellerRequestDto);
        seller.setUserId(userId);
        seller.setSellerStatus("PENDING");

        Seller savedSeller = sellerRepository.save(seller);

        return sellerMapper.toSellerResponseDto(user, savedSeller);
    }

    @Override
    public SellerResponseDto getSellerById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with id " + userId + " not found")
        );

        Seller seller = sellerRepository.findById(userId).orElseThrow(
                () -> new SellerNotFoundException("Seller with user id " + userId + " not found")
        );

        return sellerMapper.toSellerResponseDto(user, seller);
    }

    @Override
    public SellerResponseDto updateSeller(String userId, SellerRequestDto sellerRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with id " + userId + " not found")
        );

        Seller existingSeller = sellerRepository.findById(userId).orElseThrow(
                () -> new SellerNotFoundException("Seller with user id " + userId + " not found")
        );

        existingSeller.setDescription(sellerRequestDto.getDescription());
        existingSeller.setSellerStatus(sellerRequestDto.getSellerStatus());
        existingSeller.setSellerUpdatedDate(LocalDateTime.now());

        Seller updatedSeller = sellerRepository.save(existingSeller);

        return sellerMapper.toSellerResponseDto(user, updatedSeller);
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {
        return List.of();
    }

    @Override
    public void deleteSeller(String userId) {

    }

}
