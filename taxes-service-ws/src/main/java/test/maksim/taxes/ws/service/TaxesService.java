package test.maksim.taxes.ws.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.maksim.taxes.domain.dto.InputProductData;
import test.maksim.taxes.domain.dto.OutputProductData;
import test.maksim.taxes.domain.dto.TaxesCalculationResponse;
import test.maksim.taxes.ws.calculator.TaxesCalculator;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TaxesService {

    private final TaxesCalculator taxesCalculator;

    public TaxesCalculationResponse calculateTaxes(List<InputProductData> productDataList) {
        List<OutputProductData> outputProductDataList = productDataList.stream()
                .map(this::getOutputProductData)
                .collect(toList());

        return TaxesCalculationResponse.builder()
                .products(outputProductDataList)
                .build();
    }

    private OutputProductData getOutputProductData(InputProductData productData) {
        return OutputProductData.builder()
                .productData(productData)
                .itemTaxes(taxesCalculator.calculate(productData))
                .build();
    }
}
