package com.aleksandr.shortener.unit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class ArchitectureTest {

    private static final String PACKAGE = "com.aleksandr.shortener";
    private static final String APPLICATION = "Application";
    private static final String DOMAIN = "Domain";
    private static final String INFRASTRUCTURE = "Infrastructure";
    private static final String PRESENTATION = "Presentation";

    private static final List<String> RESTRICTED_DOMAIN_ACCESSES = List.of(
            "com.amazonaws..",
            "software.amazon..",

            "org.springframework.http..",
            "org.springframework.web..",
            "org.springframework.boot.."
    );

    private final JavaClasses classes = new ClassFileImporter(List.of(new ImportOption.DoNotIncludeTests()))
            .importPackages(PACKAGE);

    @Test
    void verifyDomainLayer() {
        GivenClassesConjunction given = noClasses().that().resideInAPackage(PACKAGE + ".domain..");

        ClassesShouldConjunction classesShould = given.should().accessClassesThat()
                .resideInAPackage(RESTRICTED_DOMAIN_ACCESSES.getFirst());

        for (int i = 1; i < RESTRICTED_DOMAIN_ACCESSES.size(); i++) {
            classesShould = classesShould.orShould().accessClassesThat()
                    .resideInAPackage(RESTRICTED_DOMAIN_ACCESSES.get(i));
        }

        classesShould.check(classes);
    }

    @Test
    void verifyLayeredDependencies() {
        layeredArchitecture().consideringAllDependencies()
                .optionalLayer(PRESENTATION).definedBy(PACKAGE + ".presentation..")
                .optionalLayer(APPLICATION).definedBy(PACKAGE + ".application..")
                .optionalLayer(DOMAIN).definedBy(PACKAGE + ".domain..")
                .optionalLayer(INFRASTRUCTURE).definedBy(PACKAGE + ".infrastructure..")

                .whereLayer(INFRASTRUCTURE).mayNotBeAccessedByAnyLayer()
                .whereLayer(PRESENTATION).mayOnlyBeAccessedByLayers(INFRASTRUCTURE)
                .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(PRESENTATION, INFRASTRUCTURE)
                .whereLayer(DOMAIN).mayOnlyBeAccessedByLayers(PRESENTATION, APPLICATION, INFRASTRUCTURE)

                .check(classes);
    }

}
