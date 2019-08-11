package test.maksim.taxes.ws.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.maksim.taxes.domain.constants.Endpoints;
import test.maksim.taxes.domain.dto.InputProductData;
import test.maksim.taxes.domain.dto.TaxesCalculationResponse;
import test.maksim.taxes.ws.service.TaxesService;

import java.util.List;

@RestController
@RequestMapping(Endpoints.V1_PREFIX)
@RequiredArgsConstructor
@Slf4j
@Api
public class TaxesController {

    private final AsyncListenableTaskExecutor serviceExecutor;
    private final TaxesService taxesService;

    @PostMapping(Endpoints.CALCULATE)
    public ListenableFuture<TaxesCalculationResponse> calculateTaxes(@RequestBody List<InputProductData> requests) {
        log.info("Received tax calculation requests: {}", requests);
        return serviceExecutor.submitListenable(() -> taxesService.calculateTaxes(requests));
    }
}
