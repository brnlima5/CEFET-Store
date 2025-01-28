package com.facul.cefet_store.services.jwt.admin.faq;

import com.facul.cefet_store.dto.FAQDto;

public interface FAQService {

    FAQDto postFAQ(Long produtoId, FAQDto faqDto);

}
