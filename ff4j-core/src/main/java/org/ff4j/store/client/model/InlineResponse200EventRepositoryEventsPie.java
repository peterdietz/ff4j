/*
 * FF4J (ff4j.org) WebAPI
 * Administrate and operate all tasks on your features through this api.
 *
 * OpenAPI spec version: 1.5
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.ff4j.store.client.model;

/*
 * #%L
 * ff4j-core
 * %%
 * Copyright (C) 2013 - 2017 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.ff4j.store.client.model.InlineResponse200EventRepositoryBarChartSeries;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * InlineResponse200EventRepositoryEventsPie
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-07-27T20:07:45.848Z")
public class InlineResponse200EventRepositoryEventsPie {
  @SerializedName("sectors")
  private List<InlineResponse200EventRepositoryBarChartSeries> sectors = null;

  @SerializedName("title")
  private String title = null;

  public InlineResponse200EventRepositoryEventsPie sectors(List<InlineResponse200EventRepositoryBarChartSeries> sectors) {
    this.sectors = sectors;
    return this;
  }

  public InlineResponse200EventRepositoryEventsPie addSectorsItem(InlineResponse200EventRepositoryBarChartSeries sectorsItem) {
    if (this.sectors == null) {
      this.sectors = new ArrayList<InlineResponse200EventRepositoryBarChartSeries>();
    }
    this.sectors.add(sectorsItem);
    return this;
  }

   /**
   * Get sectors
   * @return sectors
  **/
  @ApiModelProperty(value = "")
  public List<InlineResponse200EventRepositoryBarChartSeries> getSectors() {
    return sectors;
  }

  public void setSectors(List<InlineResponse200EventRepositoryBarChartSeries> sectors) {
    this.sectors = sectors;
  }

  public InlineResponse200EventRepositoryEventsPie title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(value = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200EventRepositoryEventsPie inlineResponse200EventRepositoryEventsPie = (InlineResponse200EventRepositoryEventsPie) o;
    return Objects.equals(this.sectors, inlineResponse200EventRepositoryEventsPie.sectors) &&
        Objects.equals(this.title, inlineResponse200EventRepositoryEventsPie.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sectors, title);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200EventRepositoryEventsPie {\n");
    
    sb.append("    sectors: ").append(toIndentedString(sectors)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}
