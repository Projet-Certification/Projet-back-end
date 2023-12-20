package backend.Projetcertification.dto.mapper;

import backend.Projetcertification.dto.CanalDTO;
import backend.Projetcertification.dto.CanalPutDto;
import backend.Projetcertification.entity.Canal;


public class CanalMapper {
    public static CanalDTO entityToDto(Canal entity) {
        CanalDTO canalDTO = new CanalDTO();
        canalDTO.setId(entity.getId());
        canalDTO.setNomCanal(entity.getNomCanal());
        canalDTO.setEstActif(entity.isEstActif());
        canalDTO.setEstLeGeneral(entity.isEstGeneral());
        return canalDTO;
    }
    public static Canal dtoToEntity(CanalDTO canalDTO) {
        Canal canal = new Canal();
        canal.setNomCanal(canalDTO.getNomCanal());
        canal.setEstActif(canalDTO.isEstActif());
        canal.setEstGeneral(canalDTO.isEstLeGeneral());
        return canal;
    }

    public static CanalDTO dtoToDtoPut(CanalPutDto  entity) {
        CanalDTO canalDTO = new CanalDTO();
        canalDTO.setNomCanal(entity.getNomCanal());
        canalDTO.setEstLeGeneral(entity.isEstLeGeneral());
        canalDTO.setEstActif(entity.isEstActif());
        return canalDTO;
    }
}
