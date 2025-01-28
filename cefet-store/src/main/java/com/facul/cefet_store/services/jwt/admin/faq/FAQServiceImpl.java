package com.facul.cefet_store.services.jwt.admin.faq;

import com.facul.cefet_store.dto.FAQDto;
import com.facul.cefet_store.entity.FAQ;
import com.facul.cefet_store.entity.Produto;
import com.facul.cefet_store.repository.FAQRepository;
import com.facul.cefet_store.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

    private final FAQRepository faqRepository;

    private final ProdutoRepository produtoRepository;


    public FAQDto postFAQ(Long produtoId, FAQDto faqDto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);

        if(optionalProduto.isPresent()) {
            FAQ faq = new FAQ();

            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduto.get());

            return faqRepository.save(faq).getFAQDto();
        }
        return null;
    }

}
