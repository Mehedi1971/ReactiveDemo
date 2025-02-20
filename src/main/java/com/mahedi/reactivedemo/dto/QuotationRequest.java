package com.mahedi.reactivedemo.dto;

import com.mahedi.reactivedemo.model.BillItem;
import com.mahedi.reactivedemo.model.Quotation;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuotationRequest extends BaseDto{
  private Quotation quotation;
  private List<BillItem> billItems;
}
