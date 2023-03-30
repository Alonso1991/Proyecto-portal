package arg.mercadopago.mercadofood.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description ="status response")
@JsonPropertyOrder({"status"})
public class ResponseDTO {

        @ApiModelProperty(notes = "Estado respuesta")
        private String status;

        @ApiModelProperty(notes = "Descripcion Estado respuesta")
        private String descStatus;

        public ResponseDTO(String status, String descStatus) {
                this.status = status;
                this.descStatus = descStatus;
        }

        public ResponseDTO() {
        }
}
