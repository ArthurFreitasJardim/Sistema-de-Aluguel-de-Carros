package com.example.service;

import com.example.model.CarroDisponivel;
import com.example.model.TipoProprietario;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class CarroDisponivelService {

    public List<CarroDisponivel> listar() {
        return List.of(
                carro("corolla", "Toyota", "Corolla", 2024, "18.000 km", 189.90,
                        "Sedan confortável, econômico e ideal para viagens.",
                        "MAT-2024-001", "ABC1D23", TipoProprietario.EMPRESA),

                carro("civic", "Honda", "Civic", 2023, "24.000 km", 199.90,
                        "Sedan esportivo com ótimo desempenho e acabamento refinado.",
                        "MAT-2023-002", "BCD2E34", TipoProprietario.BANCO),

                carro("hb20", "Hyundai", "HB20", 2023, "16.500 km", 119.90,
                        "Compacto, econômico e excelente para uso urbano.",
                        "MAT-2023-003", "CDE3F45", TipoProprietario.EMPRESA),

                carro("onix", "Chevrolet", "Onix", 2024, "12.000 km", 129.90,
                        "Hatch moderno, eficiente e muito confortável para o dia a dia.",
                        "MAT-2024-004", "DEF4G56", TipoProprietario.EMPRESA),

                carro("compass", "Jeep", "Compass", 2023, "31.000 km", 259.90,
                        "SUV robusto, espaçoso e ideal para viagens em família.",
                        "MAT-2023-005", "EFG5H67", TipoProprietario.BANCO),

                carro("tcross", "Volkswagen", "T-Cross", 2024, "15.200 km", 239.90,
                        "SUV compacto com ótimo espaço interno e tecnologia.",
                        "MAT-2024-006", "FGH6I78", TipoProprietario.EMPRESA),

                carro("argo", "Fiat", "Argo", 2023, "20.000 km", 109.90,
                        "Hatch versátil, econômico e confortável para uso urbano.",
                        "MAT-2023-007", "GHI7J89", TipoProprietario.EMPRESA),

                carro("mobi", "Fiat", "Mobi", 2024, "9.500 km", 89.90,
                        "Compacto prático, fácil de estacionar e econômico.",
                        "MAT-2024-008", "HIJ8K90", TipoProprietario.EMPRESA),

                carro("cronos", "Fiat", "Cronos", 2023, "22.000 km", 129.90,
                        "Sedan compacto com bom porta-malas e ótimo custo-benefício.",
                        "MAT-2023-009", "IJK9L01", TipoProprietario.BANCO),

                carro("toro", "Fiat", "Toro", 2024, "18.800 km", 269.90,
                        "Picape urbana confortável, forte e versátil.",
                        "MAT-2024-010", "JKL1M12", TipoProprietario.EMPRESA),

                carro("polo", "Volkswagen", "Polo", 2024, "13.000 km", 139.90,
                        "Hatch premium compacto, seguro e com boa dirigibilidade.",
                        "MAT-2024-011", "KLM2N23", TipoProprietario.EMPRESA),

                carro("nivus", "Volkswagen", "Nivus", 2023, "19.000 km", 219.90,
                        "SUV cupê moderno, tecnológico e confortável.",
                        "MAT-2023-012", "LMN3O34", TipoProprietario.BANCO),

                carro("virtus", "Volkswagen", "Virtus", 2024, "11.500 km", 169.90,
                        "Sedan espaçoso, elegante e ideal para estrada.",
                        "MAT-2024-013", "MNO4P45", TipoProprietario.EMPRESA),

                carro("saveiro", "Volkswagen", "Saveiro", 2023, "27.000 km", 159.90,
                        "Picape leve, prática e ideal para transporte urbano.",
                        "MAT-2023-014", "NOP5Q56", TipoProprietario.EMPRESA),

                carro("tracker", "Chevrolet", "Tracker", 2024, "14.400 km", 229.90,
                        "SUV moderno, confortável e com bom consumo.",
                        "MAT-2024-015", "OPQ6R67", TipoProprietario.BANCO),

                carro("spin", "Chevrolet", "Spin", 2023, "25.000 km", 179.90,
                        "Minivan espaçosa, ideal para famílias e viagens.",
                        "MAT-2023-016", "PQR7S78", TipoProprietario.EMPRESA),

                carro("montana", "Chevrolet", "Montana", 2024, "10.200 km", 219.90,
                        "Picape compacta moderna, confortável e eficiente.",
                        "MAT-2024-017", "QRS8T89", TipoProprietario.EMPRESA),

                carro("creta", "Hyundai", "Creta", 2024, "16.000 km", 239.90,
                        "SUV confortável, tecnológico e com ótimo espaço interno.",
                        "MAT-2024-018", "RST9U90", TipoProprietario.BANCO),

                carro("kwid", "Renault", "Kwid", 2024, "8.900 km", 89.90,
                        "Compacto econômico, prático e ideal para cidade.",
                        "MAT-2024-019", "STU1V01", TipoProprietario.EMPRESA),

                carro("duster", "Renault", "Duster", 2023, "23.000 km", 199.90,
                        "SUV robusto, espaçoso e preparado para diferentes usos.",
                        "MAT-2023-020", "TUV2W12", TipoProprietario.EMPRESA),

                carro("208", "Peugeot", "208", 2024, "12.700 km", 149.90,
                        "Hatch moderno, bonito e com acabamento sofisticado.",
                        "MAT-2024-021", "UVW3X23", TipoProprietario.BANCO),

                carro("c3", "Citroën", "C3", 2024, "10.500 km", 129.90,
                        "Hatch compacto, estiloso e confortável para uso urbano.",
                        "MAT-2024-022", "VWX4Y34", TipoProprietario.EMPRESA),

                carro("kicks", "Nissan", "Kicks", 2023, "21.000 km", 219.90,
                        "SUV econômico, confortável e confiável.",
                        "MAT-2023-023", "WXY5Z45", TipoProprietario.BANCO),

                carro("yaris", "Toyota", "Yaris", 2024, "13.800 km", 159.90,
                        "Hatch compacto premium, econômico e confiável.",
                        "MAT-2024-024", "XYZ6A56", TipoProprietario.EMPRESA)
        );
    }

    public Optional<CarroDisponivel> buscarPorCodigo(String codigo) {
        return listar()
                .stream()
                .filter(carro -> carro.getCodigo().equals(codigo))
                .findFirst();
    }

    private CarroDisponivel carro(String codigo, String marca, String modelo, Integer ano, String km,
                                  Double precoDiaria, String descricao, String matricula, String placa,
                                  TipoProprietario proprietarioTipo) {
        return new CarroDisponivel(
                codigo,
                marca,
                modelo,
                ano,
                km,
                precoDiaria,
                imagens(codigo),
                descricao,
                matricula,
                placa,
                proprietarioTipo
        );
    }

    private List<String> imagens(String codigo) {
    return switch (codigo) {
        case "corolla" -> List.of(
                "https://cdn-site-seminovos.localiza.com/prd/site/anuncio/396617/toyota-corolla-xei-20-16v-dohc-flex-4p-car--automtico-2024-preto-automatico-seminovo-396617-21fea5d9-13bc-40ea-b99b-07eb0620db5d-1.jpg",
                "https://cdn-site-seminovos.localiza.com/prd/site/anuncio/396617/toyota-corolla-xei-20-16v-dohc-flex-4p-car--automtico-2024-preto-automatico-seminovo-396617-be65e4c8-aac4-4d20-9b35-f45d4c320bf0-1.jpg",
                "https://cdn-site-seminovos.localiza.com/prd/site/anuncio/396617/toyota-corolla-xei-20-16v-dohc-flex-4p-car--automtico-2024-preto-automatico-seminovo-396617-9bebfac1-5b12-4465-b8e1-0b53179d685e-1.jpg",
                "https://cdn-site-seminovos.localiza.com/prd/site/anuncio/396617/toyota-corolla-xei-20-16v-dohc-flex-4p-car--automtico-2024-preto-automatico-seminovo-396617-ee6d27a4-c1a5-4e50-aba7-268eb88d7e60-1.jpg",
                "https://cdn-site-seminovos.localiza.com/prd/site/anuncio/396617/toyota-corolla-xei-20-16v-dohc-flex-4p-car--automtico-2024-preto-automatico-seminovo-396617-dd72dbf8-5258-41ef-aa37-a872c34759d7-1.jpg"
        );

        case "civic" -> List.of(
                "https://site.com/civic1.jpg",
                "https://site.com/civic2.jpg",
                "https://site.com/civic3.jpg",
                "https://site.com/civic4.jpg",
                "https://site.com/civic5.jpg"
        );

        case "hb20" -> List.of(
                "https://site.com/hb201.jpg",
                "https://site.com/hb202.jpg",
                "https://site.com/hb203.jpg",
                "https://site.com/hb204.jpg",
                "https://site.com/hb205.jpg"
        );

        default -> List.of(
                "https://images.unsplash.com/photo-1494976388531-d1058494cdd8",
                "https://images.unsplash.com/photo-1503376780353-7e6692767b70",
                "https://images.unsplash.com/photo-1542362567-b07e54358753",
                "https://images.unsplash.com/photo-1552519507-da3b142c6e3d",
                "https://images.unsplash.com/photo-1549924231-f129b911e442"
        );
    };
}

    private String normalizar(String texto) {
        return texto
                .toLowerCase()
                .replace(" ", ",")
                .replace("ç", "c")
                .replace("ã", "a")
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u");
    }
}