package test.maksim.taxes.ws.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.maksim.example.domain.dto.InputProductData;
import test.maksim.example.domain.dto.OutputProductData;
import test.maksim.example.domain.dto.TaxesCalculationResponse;
import test.maksim.taxes.ws.calculator.TaxesCalculator;
import test.maksim.taxes.ws.calculator.TotalPriceCalculator;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TaxesService {

    private final TaxesCalculator taxesCalculator;
    private final TotalPriceCalculator totalPriceCalculator;

    public TaxesCalculationResponse calculateTaxes(List<InputProductData> inputProductData) {
        List<OutputProductData> outputProductDataList = inputProductData.stream()
                .map(this::getOutputProductData)
                .collect(toList());

        return TaxesCalculationResponse.builder()
                .products(outputProductDataList)
                .totalTaxes(totalPriceCalculator.calculateTaxes(outputProductDataList))
                .totalPrice(totalPriceCalculator.calculatePrice(outputProductDataList))
                .build();
    }

    private OutputProductData getOutputProductData(InputProductData productData) {
        return OutputProductData.builder()
                .productData(productData)
                .itemTaxes(taxesCalculator.calculate(productData))
                .build();
    }
}
