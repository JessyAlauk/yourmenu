package com.br.yourmenu.core.gateway;

import com.br.yourmenu.core.domain.CustomerDomain;

public interface CustomerGateway {

    CustomerDomain save(CustomerDomain customerDomain);
}
